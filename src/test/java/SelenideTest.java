import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.headless = true;
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    void setUp() {
        open("https://github.com/");
        $("input[name='q']").setValue("selenide").submit();
        $(linkText("selenide/selenide")).click();
        $("#wiki-tab").click();
        $(withText("more pages")).click();
    }

    @Test
    @DisplayName("Make sure that there is a SoftAssertions page in the list of Pages")
    void checkSoftAssertionsExistOnPagesList() {
        $("#wiki-pages-box.wiki-pages-box").shouldHave(text("SoftAssertions"));
    }

    @Test
    @DisplayName("Open the SoftAssertions page, check that there is a sample code for JUnit5 inside")
    void checkJUnit5ExampleOnSoftAssertionsWebPage() {
        $("div[id='wiki-pages-box']").find(linkText("SoftAssertions")).click();
        $$(".highlight-source-java").findBy(text("@ExtendWith")).shouldHave(text("@ExtendWith"));
        $$(".highlight-source-java").findBy(text("@RegisterExtension")).shouldHave(text("@RegisterExtension"));
    }
}
