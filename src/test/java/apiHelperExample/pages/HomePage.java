package apiHelperExample.pages;


import com.epam.reportportal.annotations.Step;
import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.service.ReportPortal;
import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.Date;


/**
 * Sample page
 */
public class HomePage extends Page {

    @FindBy(css = ".header-search-input")
    @CacheLookup
    public WebElement searchField;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Открыта страница {baseUrl}")
    public void open(String baseUrl) {
        driver.get(baseUrl);
    }

    @Step("Выполнен поиск репозиториев по ключевому слову {value}")
    @SneakyThrows
    public RepositoriesPage searchForRepositories(String value) {
        waitForElement(searchField).sendKeys(value);
        waitForElement(searchField).sendKeys(Keys.ENTER);
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File file = scrShot.getScreenshotAs(OutputType.FILE);
        ReportPortal.emitLaunchLog("test message for ReportPortal", "INFO", new Date(), file);
        return new RepositoriesPage(driver);
    }
}
