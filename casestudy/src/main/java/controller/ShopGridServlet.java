package controller;

import Util.AppUtil;
import Util.RunnableCustom;
import dao.ProductDAO;
import model.Cart;
import model.Login;
import service.CartService;
import service.ICartService;
import service.ProductService;
import service.dto.PageableRequest;
import service.dto.enums.ESortType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "ShopGridServlet", urlPatterns = "/shop")
public class ShopGridServlet extends HttpServlet {
    private Map<String, RunnableCustom> validators;
    private ICartService iCartService;

    private final Map<String, String> errors = new HashMap<>();


    private ProductDAO productDAO = new ProductDAO();
    @Override
    public void init() {
        validators = new HashMap<>();
        iCartService = new CartService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageableRequest request = new PageableRequest(
                req.getParameter("search"),
                req.getParameter("sortField"),
                ESortType.valueOf(AppUtil.getParameterWithDefaultValue(req,"sortType", ESortType.DESC).toString()),
                Integer.parseInt(AppUtil.getParameterWithDefaultValue(req, "page", "1").toString()),
                Integer.parseInt(AppUtil.getParameterWithDefaultValue(req, "limit", "3").toString())
        );
        Login login = (Login) req.getSession().getAttribute("login");
        if(login != null) {
            Cart cart = iCartService.getCartById(login.getId());
            req.setAttribute("cart", cart);
        }

        req.setAttribute("pageable", request);
        req.setAttribute("products", ProductService.getProductService().getProducts(request));
        RequestDispatcher rs = req.getRequestDispatcher("shop_grid.jsp");
        rs.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
