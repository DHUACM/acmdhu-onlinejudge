package performance.test;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import webservice.client.WSUserAccountClient;
import cn.edu.dhu.acm.oj.buslogic.facade.user.LoginForm;

public class LoginTest extends AbstractJavaSamplerClient {

    private SampleResult results;
    private String username;
    private String password;
//    private String testStr;

    public void setupTest(JavaSamplerContext arg0) {
        results = new SampleResult();
        username = arg0.getParameter("username");
        password = arg0.getParameter("password");
//        testStr = arg0.getParameter("testString", "");
//        if (testStr != null && testStr.length() > 0) {
//            results.setSamplerData(testStr);
//        }
    }

    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("username", "");
        params.addArgument("password", "");
        return params;
    }

    public SampleResult runTest(JavaSamplerContext arg0) {
        try {
            results.sampleStart();
            WSUserAccountClient client = new WSUserAccountClient("acm.dhu.edu.cn");
            LoginForm lf = new LoginForm();
            lf.setUsername(username);
            lf.setPassword(password);
            boolean result = client.login(lf);
            results.sampleEnd();

            System.out.println("login test " + username + "/" + password + " " + result);
            results.setSuccessful(result);
        } catch (Exception e) {
            System.out.println("login test exception " + e);
            results.setSuccessful(false);
        }
        return results;
    }

    public void teardownTest(JavaSamplerContext arg0) {
    }
}
