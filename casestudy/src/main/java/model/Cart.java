package model;

import java.time.LocalDate;
import java.util.List;

public class Cart {
    private long id;
    private LocalDate createAt;
    List<CartItem> cartItems;

    private double total;

    private long idLogin;

    public long getIdLogin() {
        return idLogin;
    }

    public Cart(long id, LocalDate createAt, double total, long idLogin) {
        this.id = id;
        this.createAt = createAt;
        this.total = total;
        this.idLogin = idLogin;
    }
    public Cart(LocalDate createAt, double total, long idLogin) {
        this.createAt = createAt;
        this.total = total;
        this.idLogin = idLogin;
    }

    public void setIdLogin(long idLogin) {
        this.idLogin = idLogin;
    }

    public Cart() {
    }

    public Cart(long id, LocalDate createAt, List<CartItem> cartItems, double total) {
        this.id = id;
        this.createAt = createAt;
        this.cartItems = cartItems;
        this.total = total;
    }
    public Cart(long id, LocalDate createAt, double total) {
        this.id = id;
        this.createAt = createAt;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    public void updateTotal() {
        double total = 0;
        if (cartItems != null) {
            for (int i = 0; i < cartItems.size(); i++) {
                total += cartItems.get(i).getQuantity() * cartItems.get(i).getPrice().intValue();
            }
        }
        this.total = total;
    }
}