package service;

import dao.AuthDAO;

import model.Login;
import model.enums.ERole;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;

public class AuthService {

    private final AuthDAO authDAO = new AuthDAO();

    public void register(Login login){
        login.setPassword(BCrypt.hashpw(login.getPassword(), BCrypt.gensalt(12)));
        login.setRole(ERole.USER);
        authDAO.register(login);
    }

    public Login login(Login login, HttpServletRequest request) throws RuntimeException{
        final String message = "Username or password incorrect";
        var loginInDb = authDAO.findByUsername(login.getUsername())
                .orElseThrow(() -> new RuntimeException(message));

        if(BCrypt.checkpw(login.getPassword(), loginInDb.getPassword())){
            var session = request.getSession();
            session.setAttribute("login", loginInDb);
            return loginInDb;
        }else{
            throw new RuntimeException(message);
        }
    }
}