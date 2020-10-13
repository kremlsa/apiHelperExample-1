package apiHelperExample;


import com.epam.reportportal.junit5.ReportPortalExtension;
import com.epam.reportportal.service.ReportPortal;
import com.google.common.io.Resources;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Base class for all the JUnit-based test classes
 */
@ExtendWith(ReportPortalExtension.class)
public class JUnitTestBase {

    protected static String baseUrl;
    protected WebDriver driver;

    @BeforeAll
    public static void loadConfig() {
        SuiteConfiguration config = ConfigFactory.create(SuiteConfiguration.class);
        RestAssured.baseURI = config.githubUrl();
        RestAssured.basePath = config.githubRepositoriesPath();
        baseUrl = config.siteUrl();
    }

    @SneakyThrows
    @BeforeEach
    public void initDriver()  {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//        String slenoidURL = "http://localhost:4444/wd/hub";
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setBrowserName("chrome");
//        caps.setVersion("86.0");
//        caps.setCapability("enableVNC", true);
//        caps.setCapability("screenResolution", "1280x1024");
//        caps.setCapability("enableVideo", true);
//        caps.setCapability("enableLog", true);
//
//        driver = new RemoteWebDriver(new URL(slenoidURL), caps);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
