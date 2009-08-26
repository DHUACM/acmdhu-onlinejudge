package dhuoj.primeserver.buslogic;

import dhuoj.primeserver.common.form.LoginForm;
import dhuoj.primeserver.common.form.RegisterForm;
import dhuoj.primeserver.exception.UserRegisterFailException;
import javax.ejb.Remote;

@Remote
public interface UserFacadeRemote {

    Boolean login(LoginForm loginForm);

    Boolean register(RegisterForm regForm) throws UserRegisterFailException;
    
}
