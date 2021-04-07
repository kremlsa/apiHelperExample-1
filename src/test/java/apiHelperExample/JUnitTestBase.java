package apiHelperExample;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Base class for all the JUnit-based test classes
 */
public class JUnitTestBase {

    protected static String baseUrl;
    protected WebDriver driver;

    @BeforeAll
    public static void loadConfig() throws Throwable {
        SuiteConfiguration config = new SuiteConfiguration();
        RestAssured.baseURI = config.getProperty("github.url");
        RestAssured.basePath = config.getProperty("github.repositories.path");
        baseUrl = config.getProperty("site.url");
    }

    @SneakyThrows
    @BeforeEach
    public void initDriver()  {
        String slenoidURL = "http://192.168.43.239:4444/wd/hub"; //111.11.11.11 - your IP adress
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(System.getProperty("browser_name", "chrome"));
        caps.setVersion(System.getProperty("browser_version", "86.0"));
        caps.setCapability("enableVNC", true);
        caps.setCapability("screenResolution", "1280x1024");
        caps.setCapability("enableVideo", false);
        caps.setCapability("enableLog", true);


        driver = new RemoteWebDriver(new URL(slenoidURL), caps);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
