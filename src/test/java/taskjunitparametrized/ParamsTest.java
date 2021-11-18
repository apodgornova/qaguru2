package taskjunitparametrized;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ParamsTest extends TestBase{

    /*Написать свои параметризованные тесты,
    НЕ связанные с поиском в яндексе или гугле.
    Помимо самих тестов, оформить тесткейсы к ним в текстовых файлах.
    В идеале опробовать максимальное количество разных вариантов Argument-провайдеров (разные аннотации)*/

    //1.ValueSource
    @ValueSource(strings = {"Maven", "Gradle"})
    @Tag("blocker")
    @ParameterizedTest(name = "Поиск слова {0} и проверка отображения текста {0}")
    void searchGihubTestValue(String argument) {

        open("https://github.com/");
        $(".header-search-input").setValue(argument);
        $(".header-search-input").pressEnter();
        $$(".v-align-middle").
                 find(text(argument))
                .shouldBe(visible);

    }

    //2.MethodSource
    static Stream<Arguments> stringProvider() {
        return Stream.of(
                Arguments.of("Maven", "maven"),
                Arguments.of("Gradle", "gradle")
        );
    }

    @MethodSource("stringProvider")
    @Tag("blocker")
    @ParameterizedTest(name = "Поиск слова {0} и проверка отображения текста {1}")
    void commonGithubSearchTest(String searchQuery, String expectedResult) {

        open("https://github.com/");
        $(".header-search-input").setValue(searchQuery);
        $(".header-search-input").pressEnter();
        $$(".v-align-middle").
                find(text(expectedResult))
                .shouldBe(visible);

    }



    /*private static List<String> arguments = List.of("лаконичные и стабильные UI тесты на Java");

    @DisplayName("Поиск в ya.ru слова Selenide")
    @Tag("blocker")
    @Test
    void selenideSearchTest() {
        open("https://ya.ru");
        $("#text").setValue("Selenide");
        $("button[type='submit']").click();
        $$("li.serp-item")
                .find(Condition.text("лаконичные и стабильные UI тесты на Java"))
                .shouldBe(Condition.visible);
    }


    static Stream<Arguments> commonYaSearchTest() {
        return Stream.of(
                Arguments.of("Selenide"),
                Arguments.of("")
        );
    }

//    @EnumSource(SearchQuery.class)
    @MethodSource
//    @CsvSource(value = {
//            "Selenide| лаконичные и стабильные UI тесты на Java]",
//            "Allure| Allure"
//    },
//    delimiter = '|')
    @Tag("blocker")
//    @ValueSource(strings = {"Allure", "Selenide"})
    @DisplayName("Поиск в яндексе")
    @ParameterizedTest(name = "Поиск в ya.ru слова {0} и проверка отображения текста {1}")
    void commonYaSearchTest(String searchQuery, List<String> expectedResult) {
        open("https://ya.ru");
        $("#text").setValue(searchQuery);
        $("button[type='submit']").click();
        $$("li.serp-item")
                .find(Condition.text(expectedResult.get(0)))
                .shouldBe(Condition.visible);
    }
}*/
}
