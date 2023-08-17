package service;

import Util.AppConstant;
import dao.ProductDAO;
import model.Product;
import service.dto.PageableRequest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static List<Product> products;

    private static Long currentId = 0L;

    private static final ProductService productService;

    private ProductDAO productDAO = new ProductDAO();

    static {
        products = new ArrayList<>();
        productService = new ProductService();
    }

    public List<Product> getProducts(PageableRequest request){
        return productDAO.findAll(request);
    }


    public Product findById(Long id){
        return productDAO.findById(id)
                .orElseThrow(() ->  new RuntimeException(String.format(AppConstant.ID_NOT_FOUND, "Product")));


    }

    public void create(Product product){
        product.setCreate_at(Date.valueOf(LocalDate.now()));
        productDAO.insertProduct(product);
    }

    public static ProductService getProductService() {
        return productService;
    }
    public ProductService(){
    }

    public void edit(Product product) {

        productDAO.updateProduct(product);

    }
    public boolean existById(Long id) {
        return productDAO.findById(id).orElse(null) != null;
    }
    public void delete(Long productId) {
        productDAO.deleteById(productId);

    }

}