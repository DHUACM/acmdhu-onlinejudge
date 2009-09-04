package cn.edu.dhu.acm.oj.webservice.client;

import java.net.URL;
import javax.xml.namespace.QName;
import cn.edu.dhu.acm.oj.webservice.*;
import cn.edu.dhu.acm.oj.client.Control;

public class WSUserAccountClient {

	final private QName qName = new QName("http://webservice.oj.acm.dhu.edu.cn/", "UserAccountServiceService");
	private URL[] url;
	private int size;

	public WSUserAccountClient() throws Exception {
		size = Control.getNetList().size();
		url = new URL[size];
		for (int i = 0; i < size; i++) {
			url[i] = new URL("http://" + Control.getNetList().get(i) + "/dhuoj/UserAccountService?wsdl");
		}
	}

	public boolean login(LoginForm loginForm) throws Exception {
		int ran = (int) (size * Math.random());
		boolean ans = false;
		int k = ran;
		boolean find = false;
		Exception ex = null;
		do {
			try {
				System.out.println("login : " + url[k]);
				UserAccountServiceService service = new UserAccountServiceService(url[k], qName);
				UserAccountService port = service.getUserAccountServicePort();
				ans = port.login(loginForm);
				find = true;
				break;
			} catch (Exception e) {
				k = (k + 1) % size;
				ex = e;
			}
		} while (k != ran);
		if (!find) {
			throw ex;
		}
		return ans;
	}

	public boolean register(RegisterForm regForm) throws Exception {
		int ran = (int) (size * Math.random());
		boolean ans = false;
		int k = ran;
		boolean find = false;
		Exception ex = null;
		do {
			try {
				System.out.println("login : " + url[k]);
				UserAccountServiceService service = new UserAccountServiceService(url[k], qName);
				UserAccountService port = service.getUserAccountServicePort();
				ans = port.register(regForm);
				find = true;
				break;
			} catch (Exception e) {
				k = (k + 1) % size;
				ex = e;
			}
		} while (k != ran);
		if (!find) {
			throw ex;
		}
		return ans;
	}
}
