package controller;

import Util.AppUtil;
import model.Login;
import model.Role;
import model.enums.ERole;
import service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

@WebServlet(urlPatterns = "/auths", name = "authController")
public class AuthController  extends HttpServlet {
    private AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String page = "auths/login.jsp";
        if(Objects.equals(action, "403")){
            page = "auths/page-403.jsp";
        }
        if(Objects.equals(action, "logout")){
            req.getSession().removeAttribute("login");
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(Objects.equals(action, "login")){
            login(req,resp);
            return;
        }
        if(Objects.equals(action, "register")){
            register(req,resp);
            return;
        }
        req.getRequestDispatcher("auths/login.jsp").forward(req, resp);
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login = (Login) AppUtil.getObjectWithValidation(req, Login.class, new HashMap<>());
        authService.register(login);
        req.setAttribute("message", "Register successfully");
        req.getRequestDispatcher("auths/login.jsp").forward(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        try {
            Login loginCheck = (Login) AppUtil.getObjectWithValidation(req, Login.class, new HashMap<>());
            Login login = authService.login(loginCheck, req);
            if(login.getRole().equals(ERole.ADMIN)) {
                resp.sendRedirect("/products");
            } else if(login.getRole().equals(ERole.USER)) {
                resp.sendRedirect("/homes");
            }
        }catch (RuntimeException exception){
            req.setAttribute("message", exception.getMessage());
            req.getRequestDispatcher("auths/login.jsp").forward(req, resp);
        }
    }

}