/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.dhu.acm.oj.webservice;

import cn.edu.dhu.acm.oj.common.form.LoginForm;
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
    public Boolean login(@WebParam(name = "userForm")
    LoginForm userForm) {
        return UserFacade.login(userForm);
    }

}
