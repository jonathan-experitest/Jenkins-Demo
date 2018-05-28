package Jenkins_Demo.Jenkins_Demo;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.experitest.client.Client;
import com.experitest.client.GridClient;

public class AppTest {
	
	protected Client client = null;
	protected GridClient grid = null;
	private String testName = "Jenkins Demo - Android";
	private String accessKey = "eyJ4cC51IjoxMjcsInhwLnAiOjIsInhwLm0iOiJNVFV5TXpnM01qRTVNRFl3TnciLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE4MzkzMjYwODQsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.guEfCXtPJCKuCEMm7Xanikk2kJt-OZ2dLqBSC-8Dym8";

	@BeforeMethod
	public void setUp(){
		grid = new GridClient(accessKey, "sales.experitest.com",443, true);
		client = grid.lockDeviceForExecution(testName,"@os='android'", 10, 5);
		client.setReporter("xml", "reports", testName);
	}

	@Test
	public void test(){
		client.install("cloud:com.experitest.ExperiBank/.LoginActivity", true, false);
		client.deviceAction("Unlock");
		client.launch("cloud:com.experitest.ExperiBank/.LoginActivity", true, false);
		client.elementSendText("NATIVE", "id=usernameTextField", 0, "company");
		client.elementSendText("NATIVE", "id=passwordTextField", 0, "company");
		client.click("NATIVE", "id=loginButton", 0, 1);
	}

	@AfterMethod
	public void tearDown(){
		client.generateReport(false);
		client.releaseClient();
	}
}
