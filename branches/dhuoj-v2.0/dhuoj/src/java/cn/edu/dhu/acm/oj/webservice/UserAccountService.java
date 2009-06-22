/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.dhu.acm.oj.webservice;

import cn.edu.dhu.acm.oj.common.form.LoginForm;
import cn.edu.dhu.acm.oj.common.form.RegisterForm;
import cn.edu.dhu.acm.oj.exception.UserRegisterFailException;
import cn.edu.dhu.acm.oj.logic.facade.UserFacade;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

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
        boolean loginResult = UserFacade.login(loginForm);
        System.out.println("User " + loginForm.getUsername() + " login success " + loginResult);
        return loginResult;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "register")
    public Boolean register(@WebParam(name = "registerForm")
    RegisterForm registerForm) throws UserRegisterFailException {
        try {
            return UserFacade.register(registerForm);
        } catch(UserRegisterFailException urfe) {
            throw urfe;
        }
    }
}
