package controller;

import Util.RunnableCustom;
import model.Bill;
import model.Cart;
import model.Login;
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

@WebServlet(name = "BilldetailsServlet", urlPatterns = "/billdetail")
public class BilldetailsServlet extends HttpServlet {
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
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("bill.jsp");
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

        validateName(req, bill, errors);
        validatePhone(req, bill, errors);
        validateAddress(req, bill, errors);
        validateEmail(req, bill, errors);

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



    private void validateName(HttpServletRequest req, Bill bill, List<String> errors) throws UnsupportedEncodingException {
        String name = req.getParameter("name");

        if (name == null || name.trim().isEmpty()) {
            errors.add("Tên không được để trống. Vui lòng nhập lại");
        } else if (name.length() > 20) { // MAX_NAME_LENGTH là độ dài tối đa cho tên
            errors.add("Tên không được quá 20 kí tự. Vui lòng nhập lại");
        } else {
            bill.setName(name);
        }
    }
    private void validatePhone(HttpServletRequest req, Bill bill, List<String> errors) throws UnsupportedEncodingException {
        String phone = req.getParameter("phone");

        if (phone == null || phone.trim().isEmpty()) {
            errors.add("Số không được để trống. Vui lòng nhập lại");
        } else if (phone.length() > 10) { // MAX_NAME_LENGTH là độ dài tối đa cho tên
            errors.add("Số không được quá 10 số. Vui lòng nhập lại");
        } else {
            bill.setPhone(phone);
        }
    }
    private void validateAddress(HttpServletRequest req, Bill bill, List<String> errors) throws UnsupportedEncodingException {
        String address = req.getParameter("address");

        if (address == null || address.trim().isEmpty()) {
            errors.add("Địa chỉ không được để trống. Vui lòng nhập lại");
        } else if (address.length() > 20) { // MAX_NAME_LENGTH là độ dài tối đa cho tên
            errors.add("Địa chỉ không được quá 20 kí tự. Vui lòng nhập lại");
        } else {
            bill.setAddress(address);
        }
    }
    private void validateEmail(HttpServletRequest req, Bill bill, List<String> errors) throws UnsupportedEncodingException {
        String email = req.getParameter("email");

        if (email == null || email.trim().isEmpty()) {
            errors.add("Email không được để trống. Vui lòng nhập lại");
        } else if (email.length() > 50) { // MAX_NAME_LENGTH là độ dài tối đa cho tên
            errors.add("Email không được quá 20 kí tự. Vui lòng nhập lại");
        } else {
            bill.setEmail(email);
        }
    }


}
