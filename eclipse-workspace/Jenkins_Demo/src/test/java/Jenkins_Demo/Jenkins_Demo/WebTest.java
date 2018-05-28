package Jenkins_Demo.Jenkins_Demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;


public class WebTest {

    private WebDriver driver;
    private DesiredCapabilities dc = new DesiredCapabilities();

    @Before
    public void setUp() throws Exception {
        dc.setCapability(CapabilityType.BROWSER_NAME, System.getenv("browser"));
        dc.setCapability(CapabilityType.VERSION, "Any");
        dc.setCapability(CapabilityType.PLATFORM, Platform.ANY);
		dc.setCapability("stream", "jenkins_web");
        dc.setCapability("build.number", System.getenv("BUILD_NUMBER"));
        dc.setCapability("accessKey", System.getenv("accessKey"));
        dc.setCapability("testName", "Jenkins Demo Web");
        driver = new RemoteWebDriver(new URL(System.getenv("url")), dc);
    }


    @Test
    public void testExperitest() throws InterruptedException {
        driver.get("https://www.experitest.com/");
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/nav/div/button")));
        Thread.sleep(10000);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}