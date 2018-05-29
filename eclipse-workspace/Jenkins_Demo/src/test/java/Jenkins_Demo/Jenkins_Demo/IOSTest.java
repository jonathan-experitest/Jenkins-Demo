package Jenkins_Demo.Jenkins_Demo;

import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.experitest.appium.SeeTestClient;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class IOSTest  {

	private String testName = "Jenkins Demo iOS";
    protected IOSDriver<IOSElement> driver = null;
    protected DesiredCapabilities dc = new DesiredCapabilities();
    private SeeTestClient client;

	@Before
	public void setUp() throws Exception{
        dc.setCapability("testName", testName);
		dc.setCapability("deviceQuery", System.getenv("deviceQuery"));
		dc.setCapability("reportDirectory", "reports");
		dc.setCapability("reportFormat", "xml");
		dc.setCapability("stream", "jenkins_ios_phone");
		dc.setCapability("build.number", System.getenv("BUILD_NUMBER"));
		dc.setCapability("accessKey", System.getenv("accessKey")); 
        driver = new IOSDriver<IOSElement>(new URL(System.getenv("url")), dc);
        client = new SeeTestClient(driver);
	}

	@Test
	public void test(){
		client.install("cloud:uniqueName=testEribankIOS_" + System.getenv("BUILD_NUMBER"), true, false);
		client.launch("cloud:com.experitest.ExperiBank", true, true);
		driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='loginButton']")).click();
    }
	
	@After
	public void tearDown(){
		driver.quit();
	}
}
