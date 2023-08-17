package controller;


import dao.ProductDAO;
import model.Cart;
import model.Login;
import model.User;
import service.CartItemService;
import service.CartService;
import service.ICartItemService;
import service.ICartService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    private ProductDAO productDAO;

    private ICartService iCartService;
    private ICartItemService iCartItemService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        iCartService = new CartService();
        iCartItemService = new CartItemService();
    }
    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "add":
                addToCartView(req,resp);
                break;
            case "update":
                updateCartView(req,resp);
                break;
            case "delete":
                deleteCartView(req,resp);
                break;
            default:
                showCartView(req,resp);
                break;
        }
    }

    private void updateCartView(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Login login = (Login) req.getSession().getAttribute("login");
        if (login == null){
            resp.sendRedirect("/auths");
            return;
        }

        long idProduct = Long.parseLong(req.getParameter("id"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));


        Cart cart = iCartService.updateCartInfo(login.getId(), idProduct, quantity);
        req.setAttribute("cart", cart);
        req.getRequestDispatcher("cart.jsp").forward(req,resp);

    }

    private void showCartView(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Login login = (Login) req.getSession().getAttribute("login");
        if (login == null){
            resp.sendRedirect("/auths");
            return;
        }


        Cart cart = iCartService.getCartById(login.getId());
        req.setAttribute("cart", cart);
        req.getRequestDispatcher("cart.jsp").forward(req,resp);


    }

    private void addToCartView(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int idProduct = Integer.parseInt(req.getParameter("id"));
        int quantity = 1;
        if(req.getParameter("qty")!=null){
            quantity = Integer.parseInt(req.getParameter("qty"));
        }
        Login login = (Login) req.getSession().getAttribute("login");
        if (login == null){
            resp.sendRedirect("/auths");
            return;
        }
        iCartService.addToCart(idProduct, quantity, login.getId());

        Cart cart = iCartService.getCartById(login.getId());
        req.setAttribute("cart", cart);
        req.getRequestDispatcher("cart.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "add":
                addToCartView(req,resp);
                break;
            case "update":
                updateCartView(req,resp);
                break;
            case "delete":
                deleteCartView(req,resp);
                break;
            default:
                showCartView(req,resp);
                break;
        }

    }
    private void deleteCartView(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Login login = (Login) req.getSession().getAttribute("login");
        if (login == null) {
            resp.sendRedirect("/auths");
            return;
        }
        int id = Integer.parseInt(req.getParameter("id"));

        iCartItemService.deleteCartItem(id);
        resp.sendRedirect("/cart");
    }
}