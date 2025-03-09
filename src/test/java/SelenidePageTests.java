import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenidePageTests {

    private final String code = "@ExtendWith({SoftAssertsExtension.class})\n" +
            "class Tests {\n" +
            "  @Test\n" +
            "  void test() {\n" +
            "    Configuration.assertionMode = SOFT;\n" +
            "    open(\"page.html\");\n" +
            "\n" +
            "    $(\"#first\").should(visible).click();\n" +
            "    $(\"#second\").should(visible).click();\n" +
            "  }\n" +
            "}";

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://github.com/selenide";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920 x 1080";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    void checkPageExistenceTest() {
        String value = "SoftAssertions";
        open("/selenide");
        $("#wiki-tab").click();
        $(byText("Welcome to the selenide wiki!")).shouldBe(visible);
        $("#wiki-pages-filter").setValue(value);
        $(byText(value)).shouldBe(visible);
        $(byText(value)).click();
        $("#wiki-body").shouldHave(partialText("Using JUnit5"));
        $("#wiki-body").shouldHave(text(code));
    }
}
