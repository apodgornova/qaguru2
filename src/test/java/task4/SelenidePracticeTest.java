package task4;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class SelenidePracticeTest {

    String URL = "https://github.com/selenide/selenide";
    String URL_2 = "https://the-internet.herokuapp.com/drag_and_drop";

    @Test
    void testGithubSearch() {

        /*
        - Откройте страницу Selenide в Github
        - Перейдите в раздел Wiki проекта
        - Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        - Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
         */

        open(URL);
        $("[data-content=Wiki]").click();
        $$(".internal").shouldHave(itemWithText("Soft assertions"));
        $$(".internal").findBy(text("Soft assertions")).click();
        $(withText("JUnit5 extension")).shouldHave(text("com.codeborne.selenide.junit5.SoftAssertsExtension"));
        $(withText("Using JUnit5 extend test class")).parent().sibling(0).shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));

    }

    @Test
    void testDragNDrop() {

        /*
         - Откройте https://the-internet.herokuapp.com/drag_and_drop
        - Перенесите прямоугольник А на место В
        - Проверьте, что прямоугольники действительно поменялись
         */

        open(URL_2);
        $("#column-a header").shouldHave(text("A"));
        $("#column-b header").shouldHave(text("B"));
        //$("#column-a").dragAndDropTo("#column-b");
        actions().moveToElement($("#column-a")).clickAndHold().moveByOffset(900, 300).release().perform();
        $("#column-a header").shouldHave(text("B"));
        $("#column-b header").shouldHave(text("A"));


        sleep(10000);


    }
}
