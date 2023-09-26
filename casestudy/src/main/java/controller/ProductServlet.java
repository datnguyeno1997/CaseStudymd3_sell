package controller;

import Util.AppConstant;
import Util.AppUtil;
import Util.RunnableCustom;
import Util.RunnableWithRegex;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Category;
import model.Product;
import service.CategoryService;
import service.ProductService;
import service.dto.PageableRequest;
import service.dto.enums.ESortType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "productServlet", urlPatterns = "/products")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String PAGE = "WEB-INF/products"; // đặt hằng số

    private Map<String, RunnableCustom> validators;

    private final Map<String, String> errors = new HashMap<>();



    @Override
    public void init() {
        validators = new HashMap<>();
        validators.put("name", new RunnableWithRegex("^[A-Za-z ]{6,20}", "name", errors));
        validators.put("description", new RunnableWithRegex("^[A-Za-z ]{6,20}", "description", errors));
        validators.put("price", new RunnableWithRegex("^[0-9]{4}", "price", errors));
        validators.put("quantity", new RunnableWithRegex("^[0-9]{4}", "quantiry", errors));
        validators.put("category_id", new RunnableWithRegex("^('1'|'2')$", "category", errors));
        validators.put("author_id", new RunnableWithRegex("^('1'|'2')$", "author", errors));
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(AppConstant.ACTION);

        if(Objects.equals(action, AppConstant.EDIT)){
            showEdit(req,resp);
            return;
        }
        if (Objects.equals(action, AppConstant.CREATE)) {
            showCreate(req, resp);
            return;
        }
        if (Objects.equals(action, AppConstant.DELETE)) {
            delete(req, resp);
            return;
        }
        showList(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errors.clear(); // clear lỗi cũ
        String action = req.getParameter(AppConstant.ACTION);
        if (Objects.equals(action, AppConstant.CREATE)) {
            create(req, resp);
            return;
        }
        if (Objects.equals(action, AppConstant.EDIT)) {
            edit(req, resp);
            return;
        }
        showList(req, resp);
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Product product = getValidProduct(req,resp);
        if(errors.size() == 0){
            ProductService.getProductService().create(product);
            resp.sendRedirect("/products?message=Created");
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Product product = getValidProduct(req,resp); // lấy ra product và + xử lý cho việc validation của các field trong class Product.
        if(errors.size() == 0){ //không xảy lỗi (errors size == 0) thì mình mới sửa product.
            ProductService.getProductService().edit(product);
            resp.sendRedirect("/products?message=Edited");
        }
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PageableRequest request = new PageableRequest(
                req.getParameter("search"),
                req.getParameter("sortField"),
                ESortType.valueOf(AppUtil.getParameterWithDefaultValue(req,"sortType", ESortType.DESC).toString()),
                Integer.parseInt(AppUtil.getParameterWithDefaultValue(req, "page", "1").toString()),
                Integer.parseInt(AppUtil.getParameterWithDefaultValue(req, "limit", "5").toString())
        ); //tao doi tuong pageable voi parametter search


        req.setAttribute("pageable", request);
        req.setAttribute("products", ProductService.getProductService().getProducts(request)); // gửi qua list products để jsp vẻ lên trang web
        req.setAttribute("productsJSON", new ObjectMapper().writeValueAsString(ProductService.getProductService().getProducts(request)));
        req.setAttribute("message", req.getParameter("message")); // gửi qua message để toastr show thông báo
        req.setAttribute("categorysJSON", new ObjectMapper().writeValueAsString(CategoryService.getCategorys()));
        req.getRequestDispatcher(PAGE + AppConstant.LIST_PAGE).forward(req,resp);
    }
    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("productJSON", new ObjectMapper().writeValueAsString(new Product())); // gửi qua user rỗng để JS vẻ lên trang web
        req.setAttribute("categorysJSON", new ObjectMapper().writeValueAsString(CategoryService.getCategorys()));
        req.getRequestDispatcher(PAGE + AppConstant.CREATE_PAGE)
                .forward(req,resp);
    }
    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        if(checkIdNotFound(req, resp, id)) return;
        req.setAttribute("product", ProductService.getProductService().findById(id)); // gửi user để jsp check xem edit hay là create User
        req.setAttribute("productJSON", new ObjectMapper().writeValueAsString(ProductService.getProductService().findById(id))); // gửi qua user được tìm thấy bằng id để JS vẻ lên trang web
        req.setAttribute("categorysJSON", new ObjectMapper().writeValueAsString(CategoryService.getCategorys()));
        req.getRequestDispatcher(PAGE + AppConstant.CREATE_PAGE)
                .forward(req,resp);

    }


    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        if(checkIdNotFound(req, resp, id)) return;
        ProductService.getProductService().delete(id);
        resp.sendRedirect("/products?message=Deleted");
    }
    private Product getValidProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Product product = (Product) AppUtil.getObjectWithValidation(req, Product.class,  validators); //
        product.setCategory(new Category(Long.valueOf(req.getParameter("category_id"))));

        String pathServerImage = getServletContext().getRealPath("/") + "dashboard/img/product";
        String pathProjectImage = "D:\\module3_CaseStudy\\casestudy\\src\\main\\webapp\\dashboard\\img\\product";
        String dbImageUrl = null;
        for (Part part : req.getParts()) {
            String fileName = extractFileName(part);
            if (!fileName.equals("")) {
                fileName = new File(fileName).getName();
                if (part.getContentType().equals("image/jpeg") || part.getContentType().equals("image/png") ) {
                    part.write(pathServerImage + File.separator + fileName);
                    dbImageUrl = "dashboard/img/product" + File.separator + fileName;
                    dbImageUrl = dbImageUrl.replace("\\", "/");
                    part.write(pathProjectImage + File.separator + fileName);
                } else {
                    req.setAttribute("errorImage", "File phải là ảnh!");
                }
            }
        }
        if (dbImageUrl == null) {
            req.setAttribute("errorImage", "File ảnh không được để trống!");
        } else {
            product.setAvatar(dbImageUrl);
        }
        if(errors.size() > 0){
            req.setAttribute("productsJSON", new ObjectMapper().writeValueAsString(product)); //hiểu dòng đơn giản là muốn gửi data qua JS thì phải xài thằng này  new ObjectMapper().writeValueAsString(product).
            req.setAttribute("categorysJSON", new ObjectMapper().writeValueAsString(CategoryService.getCategorys()));
            req.setAttribute("message","Something was wrong");
            req.getRequestDispatcher(PAGE + AppConstant.CREATE_PAGE)
                    .forward(req,resp);
        }
        return product;
    }
    private boolean checkIdNotFound(HttpServletRequest req, HttpServletResponse resp, Long id) throws IOException{
        if(!ProductService.getProductService().existById(id)){
            resp.sendRedirect(PAGE + "?message=Id not found");
            return true;
        }
        return false;
    }
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}