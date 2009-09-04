package cn.edu.dhu.acm.oj.webservice.client;

import java.util.List;
import java.net.URL;
import javax.xml.namespace.QName;
import cn.edu.dhu.acm.oj.webservice.*;
import cn.edu.dhu.acm.oj.client.Control;

public class WSContestClient {

	final private QName qName = new QName("http://webservice.oj.acm.dhu.edu.cn/", "ContestServiceService");
	private URL[] url;
	private int size;

	public WSContestClient() throws Exception {
		size = Control.getNetList().size();
		url = new URL[size];
		for (int i = 0; i < size; i++) {
			url[i] = new URL("http://" + Control.getNetList().get(i) + "/dhuoj/ContestService?wsdl");
		}
	}

	public Integer submitCode(SubmitCodeForm submitCodeForm) throws Exception {
		int ran = (int) (size * Math.random());
		Integer ans = null;
		int k = ran;
		boolean find = false;
		Exception ex = null;
		do {
			try {
				System.out.println("submitCode : " + url[k]);
				ContestServiceService service = new ContestServiceService(url[k], qName);
				ContestService port = service.getContestServicePort();
				ans = port.submitCode(submitCodeForm);
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

	public SolutionBean querySubmitStatus(int solutionID) throws Exception {
		int ran = (int) (size * Math.random());
		SolutionBean ans = null;
		int k = ran;
		boolean find = false;
		Exception ex = null;
		do {
			try {
				System.out.println("querySubmitStatus : " + url[k]);
				ContestServiceService service = new ContestServiceService(url[k], qName);
				ContestService port = service.getContestServicePort();
				ans = port.querySubmitStatus(solutionID);
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

	public List<ContestBean> getContestList(int first, int max) throws Exception {
		int ran = (int) (size * Math.random());
		List<ContestBean> ans = null;
		int k = ran;
		boolean find = false;
		Exception ex = null;
		do {
			try {
				System.out.println("getContestList : " + url[k]);
				ContestServiceService service = new ContestServiceService(url[k], qName);
				ContestService port = service.getContestServicePort();
				ans = port.getContestList(first, max);
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

	public ContestBean getContestDetail(String userID, int contestID) throws Exception {
		int ran = (int) (size * Math.random());
		ContestBean ans = null;
		int k = ran;
		boolean find = false;
		Exception ex = null;
		do {
			try {
				System.out.println("getContestDetail : " + url[k]);
				ContestServiceService service = new ContestServiceService(url[k], qName);
				ContestService port = service.getContestServicePort();
				ans = port.getContestDetail(userID, contestID);
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
