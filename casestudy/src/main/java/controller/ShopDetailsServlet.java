package controller;

import Util.AppUtil;
import Util.RunnableCustom;
import dao.ProductDAO;
import model.Cart;
import model.Login;
import model.Product;
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
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ShopDetailsServlet", urlPatterns = "/product")
public class ShopDetailsServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();
    private ShopGridServlet shopGridServlet;
    private ICartService iCartService;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
        shopGridServlet = new ShopGridServlet();
        iCartService = new CartService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Optional<Product> product = productDAO.findById(id);
        req.setAttribute("p", product.orElse(null));
        PageableRequest request = new PageableRequest(
                req.getParameter("search"),
                req.getParameter("sortField"),
                ESortType.valueOf(AppUtil.getParameterWithDefaultValue(req, "sortType", ESortType.DESC).toString()),
                Integer.parseInt(AppUtil.getParameterWithDefaultValue(req, "page", "1").toString()),
                Integer.parseInt(AppUtil.getParameterWithDefaultValue(req, "limit", "4").toString())
        );
        Login login = (Login) req.getSession().getAttribute("login");
        if (login != null) {
            Cart cart = iCartService.getCartById(login.getId());
            req.setAttribute("cart", cart);
        }

        List<Product> productList = productDAO.findAll(request);
        req.setAttribute("products", productList);
        req.setAttribute("products", ProductService.getProductService().getProducts(request));
        RequestDispatcher rs = req.getRequestDispatcher("shop_details.jsp");
        rs.forward(req, resp);

    }
}

