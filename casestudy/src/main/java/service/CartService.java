package service;

import dao.DatabaseConnection;
import model.Cart;
import model.CartItem;
import model.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CartService extends DatabaseConnection implements ICartService{
    private static List<Product> products;
    private static final ProductService productService;
    static {
        products = new ArrayList<>();
        productService = new ProductService();
    }

    private ICartItemService cartItemService;
    public CartService(){
        cartItemService = new CartItemService();
    }
    @Override
    public Cart getCartById(long idLogin) {
        Cart cart = null;
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM cart where id_login = ?");

            ps.setLong(1,idLogin);
            System.out.println("getCartById" + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                LocalDate date = rs.getDate("create_at").toLocalDate();
                double total = rs.getDouble("total");
                long idLoginDB = rs.getLong("id_login");

                cart = new Cart(id,date,total,idLoginDB);
                List<CartItem> cartItems = cartItemService.getAllCartItems(cart.getId());
                cart.setCartItems(cartItems);
                cart.updateTotal();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public Cart createCart(Cart cart) {

        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `cart` (`create_at`, `total`, `id_login`) VALUES (?, ?, ?)");

            ps.setDate(1, Date.valueOf(cart.getCreateAt()));
            ps.setDouble(2,cart.getTotal());
            ps.setLong(3, cart.getIdLogin());
            ps.executeUpdate();

            System.out.println("createCart" + ps);
            ps = connection.prepareStatement("SELECT LAST_INSERT_ID() as last_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long maxId = rs.getLong("last_id");
                cart.setId(maxId);
            }
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cart;
    }


    @Override
    public void addToCart(long idProduct, long quantity, long idLogin) {
        Product product = productService.findById(idProduct);
        Cart cart = getCartById(idLogin);
        double total = product.getPrice().doubleValue() * quantity;
        if (cart == null){
            cart = new Cart(LocalDate.now(),total, idLogin);
            cart = createCart(cart);
        }
        CartItem cartItem = cartItemService.findCartItemById(cart.getId(), idProduct);
        if (cartItem != null){
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            total = product.getPrice().doubleValue() * cartItem.getQuantity();
            cart.setTotal(total);
            this.updateTotalCart(cart);
            cartItemService.updateCartItem(cartItem);
        }else {
            CartItem cartItemCreate = new CartItem(idProduct, product.getPrice(),quantity);
            cartItemCreate.setIdCart(cart.getId());
            cartItemService.saveCartItem(cartItemCreate);
        }
    }

    @Override
    public Cart updateCartInfo(long idLogin, long idProduct, long quantity) {

        Cart cart = getCartById(idLogin);
        CartItem cartItem = cartItemService.findCartItemById(cart.getId(),idProduct);
        if(cartItem != null){
            cartItem.setQuantity(quantity);
            cartItemService.updateCartItem(cartItem);
        }
        cart = getCartById(idLogin);
        return cart;
    }

    @Override
    public Cart updateTotalCart(Cart cart) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `cart` SET `total` = ? WHERE (`id` = ?)");
            ps.setDouble(1,cart.getTotal());
            ps.setLong(2,cart.getId());
            ps.executeUpdate();
            return cart;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remote(long id) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `cart` WHERE (`id`= ?)");
            ps.setLong(1,id);

            System.out.println("deleteCart" + ps);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}