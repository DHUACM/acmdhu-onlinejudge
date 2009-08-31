package cn.edu.dhu.acm.oj.webservice.client;

import java.util.List;
import java.net.URL;
import javax.xml.namespace.QName;
import cn.edu.dhu.acm.oj.webservice.*;

public class WSContestClient {

	final private QName qName = new QName("http://webservice.oj.acm.dhu.edu.cn/", "ContestServiceService");
	private URL url;

	public WSContestClient(String host) throws Exception {
		url = new URL("http://" + host + "/dhuoj/ContestService?wsdl");
	}

	public Integer submitCode(SubmitCodeForm submitCodeForm) throws Exception {
		ContestServiceService service = new ContestServiceService(url, qName);
		ContestService port = service.getContestServicePort();
		return port.submitCode(submitCodeForm);
	}

	public SolutionBean querySubmitStatus(int solutionID) throws Exception {
		ContestServiceService service = new ContestServiceService(url, qName);
		ContestService port = service.getContestServicePort();
		return port.querySubmitStatus(solutionID);
	}

	public List<ContestBean> getContestList(int first, int max) throws Exception {
		ContestServiceService service = new ContestServiceService(url, qName);
		ContestService port = service.getContestServicePort();
		return port.getContestList(first, max);
	}

	public ContestBean getContestDetail(String userID, int contestID) throws Exception {
		ContestServiceService service = new ContestServiceService(url, qName);
		ContestService port = service.getContestServicePort();
		return port.getContestDetail(userID, contestID);
	}
}
