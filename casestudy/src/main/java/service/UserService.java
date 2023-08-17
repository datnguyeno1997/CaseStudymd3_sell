package service;

import Util.AppConstant;
import dao.UserDAO;
import model.User;
import service.dto.PageableRequest;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static List<User> users;

    private static Long currentId = 0L;

    private static final UserService userService;

    private UserDAO userDAO = new UserDAO();

    static {
        users = new ArrayList<>();
        userService = new UserService();
    }

    public List<User> getUsers(PageableRequest request){
        return userDAO.findAll(request);
    }
    public User findById(Long id){
        return userDAO.findById(id)
                .orElseThrow(() ->  new RuntimeException(String.format(AppConstant.ID_NOT_FOUND, "User")));


    }
    public void create(User user){
        userDAO.insertUser(user);
    }

    public static UserService getUserService() {
        return userService;
    }
    public UserService(){}

    public void edit(User user) {
        userDAO.updateUser( user);
    }

    public boolean existById(Long id) {
        return userDAO.findById(id).orElse(null) != null;
    }

    public void delete(Long userId) {
        userDAO.deleteById(userId);

    }
}