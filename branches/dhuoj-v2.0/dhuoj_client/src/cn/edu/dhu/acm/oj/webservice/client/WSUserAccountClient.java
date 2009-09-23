package cn.edu.dhu.acm.oj.webservice.client;

import java.net.URL;
import javax.xml.namespace.QName;
import cn.edu.dhu.acm.oj.webservice.*;
import cn.edu.dhu.acm.oj.client.Control;
import javax.xml.ws.soap.SOAPFaultException;

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
		long start = 0;
		do {
			try {
				start = System.currentTimeMillis();

				System.out.println("login : " + url[k]);
				UserAccountServiceService service = new UserAccountServiceService(url[k], qName);
				long t1 = System.currentTimeMillis();
				System.out.println("T1  " + (t1 - start));
				UserAccountService port = service.getUserAccountServicePort();
				ans = port.login(loginForm);
				find = true;
				break;
			} catch (Exception e) {
				long t2 = System.currentTimeMillis();
				System.out.println("T2  " + (t2 - start));
				//k = (k + 1) % size;
				ex = e;
				break;
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
			} catch (SOAPFaultException e1) {
				System.out.println(e1.toString());
				ex = e1;
				break;
			} catch (Exception e) {
				System.out.println(e.toString());
				//k = (k + 1) % size;
				ex = e;
				break;
			}
		} while (k != ran);
		if (!find) {
			throw ex;
		}
		return ans;
	}
}
