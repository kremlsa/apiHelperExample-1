package apiHelperExample;

import apiHelperExample.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class SampleJUnitTest extends JUnitTestBase {

    private HomePage homepage;
    private RepositoriesApiHelper repositoriesApiHelper;

    @Test
    @Epic("GitHub")
    @Feature("Поиск репозиториев")
    @Story("Список найденных репозиториев содержит ключевое слово")
    @Description("Тест проверяет, что список найденных репозиториев содержит ключевое слово")
    public void testRepositoriesListSearch() {
        repositoriesApiHelper = new RepositoriesApiHelper();
        String searchQuery = "test2code";
        homepage = new HomePage(driver);
        homepage.open(baseUrl);
    }
}
