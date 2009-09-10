package webservice.client;

import java.net.URL;
import javax.xml.namespace.QName;
import cn.edu.dhu.acm.oj.webservice.*;
import cn.edu.dhu.acm.oj.buslogic.facade.user.LoginForm;
import cn.edu.dhu.acm.oj.buslogic.facade.user.RegisterForm;

public class WSUserAccountClient {

        final private QName qName = new QName("http://webservice.oj.acm.dhu.edu.cn/", "UserAccountServiceService");
        private URL url;

        public WSUserAccountClient(String host) throws java.lang.Exception {
                url = new URL("http://" + host + "/dhuoj/UserAccountService?wsdl");
        }

        public boolean login(LoginForm loginForm) throws java.lang.Exception {
                UserAccountServiceService service = new UserAccountServiceService(url, qName);
                UserAccountService port = service.getUserAccountServicePort();
                return port.login(loginForm);
        }

        public boolean register(RegisterForm regForm) throws java.lang.Exception {
                UserAccountServiceService service = new UserAccountServiceService(url, qName);
                UserAccountService port = service.getUserAccountServicePort();
                return port.register(regForm);
        }
}
