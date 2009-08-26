package dhuoj.primeserver.buslogic;

import dhuoj.primeserver.common.form.LoginForm;
import dhuoj.primeserver.common.form.RegisterForm;
import dhuoj.primeserver.exception.UserRegisterFailException;
import dhuoj.primeserver.persistence.eao.EAOFactory;
import dhuoj.primeserver.persistence.eao.UserEAO;
import dhuoj.primeserver.persistence.entity.User;
import java.util.Calendar;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

@PersistenceContext(unitName="dhuojPU",name="dhuojPU")
@Stateless
public class UserFacadeBean implements UserFacadeRemote {

    public Boolean login(LoginForm loginForm) {
        UserEAO ueao = EAOFactory.getUserEAO();
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        User user = ueao.chkLogin(username, password);
        return user == null ? false : true;
    }

    public Boolean register(RegisterForm regForm) throws UserRegisterFailException {
        UserEAO ueao = EAOFactory.getUserEAO();
        User user = ueao.findUser(regForm.getUserID());
        // if user already exist, register failed.
        if (user != null) throw new UserRegisterFailException("User " + regForm.getUserID() + " already exist!");
        user = new User(regForm.getUserID(),
                       regForm.getPassword(),
                       regForm.getEmail(),
                       regForm.getNickName(),
                       regForm.getSchool(),
                       "127.0.0.1",
                       Calendar.getInstance().getTime(),
                       Calendar.getInstance().getTime(),
                       0);
        ueao.addUser(user);
        return true;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method" or "Web Service > Add Operation")
 
}
