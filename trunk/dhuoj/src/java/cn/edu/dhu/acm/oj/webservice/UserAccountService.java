package cn.edu.dhu.acm.oj.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import cn.edu.dhu.acm.oj.wsclient.WSUserFacadeClient;
import cn.edu.dhu.acm.oj.buslogic.facade.user.*;
import cn.edu.dhu.acm.oj.config.ServerConfig;

/**
 *
 * @author yhu
 */
@WebService()
public class UserAccountService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "login")
    public Boolean login(@WebParam(name = "parameter")
    LoginForm loginForm) {
        try {
            WSUserFacadeClient client = new WSUserFacadeClient(ServerConfig.PRIME_SERVER_URL);
            boolean loginResult = client.login(loginForm);
            System.out.println("User " + loginForm.getUsername() + " login success " + loginResult);
            return loginResult;
        } catch(Exception e) {
            System.out.println("Login exception: " + e.getMessage());
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "register")
    public Boolean register(@WebParam(name = "registerForm")
    RegisterForm registerForm) throws Exception {
        WSUserFacadeClient client = new WSUserFacadeClient(ServerConfig.PRIME_SERVER_URL);
        boolean regResult = client.register(registerForm);
        System.out.println("User " + registerForm.getUserID() + " register success " + regResult);
        return regResult;
    }
}
