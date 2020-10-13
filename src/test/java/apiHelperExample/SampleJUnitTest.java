package apiHelperExample;

import apiHelperExample.pages.HomePage;
import com.epam.reportportal.junit5.ReportPortalExtension;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class SampleJUnitTest extends JUnitTestBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleJUnitTest.class);

    private HomePage homepage;

    @Test
    @DisplayName("Проверка имен найденных репозиториев при поиске по ключевому слову")
    public void testRepositoriesListSearch() {
        String searchQuery = "test2code";
        homepage = new HomePage(driver);
        homepage.open(baseUrl);
        List<String> actualRepositoriesList = homepage.searchForRepositories(searchQuery)
                .getRepoListNamesFromPage();
        assertTrue(actualRepositoriesList.stream().allMatch(item -> item.contains(searchQuery)),
                String.format("List Elements: [%s] does not contains text [%s] ", actualRepositoriesList, searchQuery));
        LOGGER.info("Список найденных репозиториев соответствует ожидаемому результату");
    }

    @Test
    @DisplayName("Проверка количества найденных репозиториев при поиске по ключевому слову")
    public void testRepositoriesListSearch1() {
        String searchQuery = "healenium";
        homepage = new HomePage(driver);
        homepage.open(baseUrl);
        List<String> actualRepositoriesList = homepage.searchForRepositories(searchQuery)
                .getRepoListNamesFromPage();
        assertThat(actualRepositoriesList.size(), equalTo(9));
        LOGGER.info("Количество найденных репозиториев соответствует ожидаемому результату");
    }
}
