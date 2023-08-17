package dao;

import Util.AppUtil;
import model.Category;
import model.Product;
import service.dto.PageableRequest;
import service.dto.enums.ESortType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAO extends DatabaseConnection{
    private final String SELECT_ALL_PRODUCTS ="SELECT p.*,c.name as `category.name`, c.id as `category.id` FROM `products` p inner join `categorys` c on p.category_id = c.id WHERE p.`name` LIKE '%s' OR p.`quantity` LIKE '%s' OR c.`name` LIKE '%s' ORDER BY %s %s LIMIT %s OFFSET %s";

    private final String SELECT_TOTAL_RECORDS = "SELECT COUNT(1) as cnt  FROM `products` p LEFT JOIN " +
            "`categorys` c on p.category_id = c.id  WHERE p.`name` like '%s' OR p.`price` LIKE '%s'";

    private final String FIND_BY_ID ="SELECT p.*, c.name as category_name FROM `products` p LEFT JOIN `categorys` c on p.category_id = c.id WHERE p.`id` = ?";

    private final String DELETE_BY_ID = "DELETE FROM `products` WHERE (`id` = ?)";


    public List<Product> findAll(PageableRequest request) {
        List<Product> products = new ArrayList<>();
        String search = request.getSearch();
        if(request.getSortField() == null){
            request.setSortField("id");
        }
        if(request.getSortType() == null){
            request.setSortType(ESortType.DESC);
        }
        if(search == null){
            search = "%%";
        }else {
            search = "%" + search + "%";
        }
        var offset = (request.getPage() - 1) * request.getLimit();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection
                     .prepareStatement(String.format(SELECT_ALL_PRODUCTS, search, search, search,
                             request.getSortField(), request.getSortType(), request.getLimit(), offset))) {

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            //

            while (rs.next()) {
                products.add(AppUtil.getObjectFromResultSet(rs, Product.class));
            }
            PreparedStatement statementTotalRecords = connection
                    .prepareStatement(String.format(SELECT_TOTAL_RECORDS, search, search));
            ResultSet resultSetOfTotalRecords = statementTotalRecords.executeQuery();
            if(resultSetOfTotalRecords.next()){
                int totalPage =
                        (int) Math.ceil(resultSetOfTotalRecords.getDouble("cnt")/request.getLimit());
                request.setTotalPage(totalPage);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    public List<Product> findAlll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS); //Trả về 1 tập các câu lệnh nen dùng executeQuery()

            System.out.println("findAll: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = getProductByResultSet(rs);
                products.add(product);
            }
            connection.close();
        } catch (SQLException exception) {
            printSQLException(exception);
        }
        return products;
    }

    public void insertProduct(Product product){
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection
                     .prepareStatement(AppUtil.buildInsertSql("products", product))) {
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(AppUtil.buildUpdateSql("products", product))) {
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Product> findById(Long id) {
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection
                     .prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(AppUtil.getObjectFromResultSet(rs, Product.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection
                     .prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Product getProductByResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        Float price = rs.getFloat("price");
        int quantity = rs.getInt("quantity");
        String avatar = rs.getString("avatar");
        String description = rs.getString("description");
        String create_at = rs.getString("create_at");
        Long categoryId = rs.getLong("category_id");
        String categoryName = rs.getString("category_name");
        Category category = new Category(categoryId, categoryName);
        return new Product(id, name, price,quantity, category, avatar, description, Date.valueOf(create_at) );
    }
}
