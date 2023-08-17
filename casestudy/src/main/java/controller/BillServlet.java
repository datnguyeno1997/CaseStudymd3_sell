package controller;

import Util.RunnableCustom;
import Util.RunnableWithRegex;
import model.Bill;
import model.Cart;
import model.Login;
import model.User;
import service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "BillServlet", urlPatterns = "/checkout")
public class BillServlet extends HttpServlet {
    private Map<String, RunnableCustom> validators;
    private final Map<String, String> errors = new HashMap<>();
    private ICartService iCartService;
    private ICartItemService iCartItemService;
    private IBillServlet billServlet = new BillServiceMysql();

    @Override
    public void init(ServletConfig config) throws ServletException {
        iCartService = new CartService();
        iCartItemService = new CartItemService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login = (Login) req.getSession().getAttribute("login");
        if (login == null) {
            resp.sendRedirect("/auths");
            return;
        }

        Cart cart = iCartService.getCartById(login.getId());

        req.setAttribute("cart", cart);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("checkout.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                break;
            default:
                savePayment(req, resp);
                break;
        }
    }



    private void savePayment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Bill bill = new Bill();
        List<String> errors = new ArrayList<>();



        Login login = (Login) req.getSession().getAttribute("login");
        if (login == null) {
            resp.sendRedirect("/auths");
            return;
        }
        Cart cart = iCartService.getCartById(login.getId());
        req.setAttribute("cart", cart);
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("checkout.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            billServlet.save(bill);
            deleteCart(cart.getId() ,req, resp);
            req.setAttribute("bill", bill);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("bill.jsp");
            requestDispatcher.forward(req, resp);
        }

    }

    private void deleteCart( long id, HttpServletRequest req, HttpServletResponse resp) {
        iCartItemService.deleteIdCart(id);
        iCartService.remote(id);
    }







}
