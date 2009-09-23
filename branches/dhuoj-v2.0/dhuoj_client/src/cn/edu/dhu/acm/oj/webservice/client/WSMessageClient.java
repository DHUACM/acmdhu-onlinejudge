package cn.edu.dhu.acm.oj.webservice.client;

import java.net.URL;
import javax.xml.namespace.QName;
import cn.edu.dhu.acm.oj.webservice.*;
import cn.edu.dhu.acm.oj.client.Control;
import javax.xml.ws.soap.SOAPFaultException;

public class WSMessageClient {

	final private QName qName = new QName("http://webservice.oj.acm.dhu.edu.cn/", "MessageServiceService");
	private URL[] url;
	private int size;

	public WSMessageClient() throws Exception {
		size = Control.getNetList().size();
		url = new URL[size];
		for (int i = 0; i < size; i++) {
			url[i] = new URL("http://" + Control.getNetList().get(i) + "/dhuoj/MessageService?wsdl");
		}
	}

	public Integer submitMessage(MessageForm msgForm) throws Exception {
		int ran = (int) (size * Math.random());
		Integer ans = null;
		int k = ran;
		boolean find = false;
		Exception ex = null;
		do {
			try {
				System.out.println("submitMessage : " + url[k]);
				MessageServiceService service = new MessageServiceService(url[k], qName);
				MessageService port = service.getMessageServicePort();
				ans = port.postMessage(msgForm);
				find = true;
				break;
			} catch (SOAPFaultException e1) {
				ex = e1;
				break;
			} catch (Exception e) {
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

	public MessageBean queryMessageStatus(int msgID) throws Exception {
		int ran = (int) (size * Math.random());
		MessageBean ans = null;
		int k = ran;
		boolean find = false;
		Exception ex = null;
		do {
			try {
				System.out.println("queryMessageStatus : " + url[k]);
				MessageServiceService service = new MessageServiceService(url[k], qName);
				MessageService port = service.getMessageServicePort();
				ans = port.queryMessageStatus(msgID);
				find = true;
				break;
			} catch (SOAPFaultException e1) {
				ex = e1;
				break;
			} catch (Exception e) {
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
