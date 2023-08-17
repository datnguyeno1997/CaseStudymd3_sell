package service;

import dao.DatabaseConnection;

import dao.ProductDAO;
import model.CartItem;
import model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemService extends DatabaseConnection implements ICartItemService{
    private static List<Product> products;
    private static final ProductService productService;

    static {
        products = new ArrayList<>();
        productService = new ProductService();
    }
    @Override
    public List<CartItem> getAllCartItems(long idCart) {
        List<CartItem> cartItems = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM cart_items where id_cart = ?");

            ps.setLong(1, idCart);
            System.out.println("getAllCartItems" + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                long idProduct = rs.getLong("id_product");
                long idCartDB = rs.getLong("id_cart");
                int quantity  = rs.getInt("quantity");
                Float price = rs.getFloat("price");

                Product product = productService.findById(idProduct);


                CartItem cartItem = new CartItem(id, idProduct, idCartDB, price, quantity );
                cartItem.setProduct(product);
                cartItems.add(cartItem);


            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cartItems;
    }

    @Override
    //Tạo mới
    public CartItem saveCartItem(CartItem cartItem) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `cart_items` (`id_product`, `id_cart`, `quantity`, `price`) VALUES (?, ?, ?, ?)");
            ps.setLong(1,cartItem.getIdProduct());
            ps.setLong(2,cartItem.getIdCart());
            ps.setLong(3,cartItem.getQuantity());
            ps.setFloat(4,cartItem.getPrice());

            System.out.println("saveCartItem: " + ps);
            ps.executeUpdate();

            ps = connection.prepareStatement("SELECT LAST_INSERT_ID() as last_id");
            System.out.println("saveCartItem" + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long lastId = rs.getLong("last_id");
                cartItem.setId(lastId);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return cartItem;
    }

    @Override
    //Sửa
    public CartItem updateCartItem(CartItem cartItem) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `cart_items` SET `quantity` = ? WHERE (`id` = ?)");
            ps.setLong(1,cartItem.getQuantity());
            ps.setLong(2,cartItem.getId());
            ps.executeUpdate();


            ps = connection.prepareStatement("SELECT * FROM `cart_items` where id = ?");

            ps.setLong(1,cartItem.getId());

            System.out.println("updateCartItem" + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                long idProduct = rs.getLong("id_product");
                long idCartDB = rs.getLong("id_cart");
                int quantity  = rs.getInt("quantity");
                Float price = rs.getFloat("price");

                CartItem cartItemUpdate = new CartItem(id, idProduct, idCartDB, price, quantity );
                return cartItemUpdate;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteCartItem(long idCartItem) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `cart_items` WHERE (`id`= ?)");
            ps.setLong(1,idCartItem);

            System.out.println("deleteCartItem" + ps);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteIdCart(long idCart) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `cart_items` WHERE (`id_cart`= ?)");
            ps.setLong(1,idCart);

            System.out.println("deleteCartItem" + ps);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public CartItem findCartItemById(long cartId, long idProduct){
        CartItem cartItem = null;
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `cart_items` where id_cart = ? and id_product = ?");
            ps.setLong(1,cartId);
            ps.setLong(2,idProduct);

            System.out.println("findCartItemById" + ps);
            ResultSet rs =  ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                long idProductDB = rs.getLong("id_product");
                long idCartDB = rs.getLong("id_cart");
                int quantity  = rs.getInt("quantity");
                Float price = rs.getFloat("price");

                cartItem = new CartItem(id, idProductDB, idCartDB, price, quantity );

            }
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return cartItem;
    }
}