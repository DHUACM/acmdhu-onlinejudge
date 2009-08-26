package dhuoj.webservice;

import dhuoj.primeserver.buslogic.UserFacadeRemote;
import dhuoj.primeserver.common.form.LoginForm;
import dhuoj.primeserver.common.form.RegisterForm;
import dhuoj.primeserver.exception.UserRegisterFailException;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class WSUserAccount {
    @EJB
    private UserFacadeRemote userFacadeBean;

    @WebMethod(operationName = "login")
    public Boolean login(@WebParam(name = "loginForm")
    LoginForm loginForm) {
        return userFacadeBean.login(loginForm);
    }

    @WebMethod(operationName = "register")
    public Boolean register(@WebParam(name = "regForm")
    RegisterForm regForm) throws UserRegisterFailException {
        return userFacadeBean.register(regForm);
    }
}
