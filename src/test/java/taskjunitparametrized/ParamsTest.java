package taskjunitparametrized;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ParamsTest extends TestBase {

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

    //3.EnumSource
    @EnumSource(SearchQuery.class)
    @Tag("blocker")
    @ParameterizedTest(name = "Поиск слова {0} и проверка отображения текста {0}")
    void commonGithubSearchTestEnum(SearchQuery query) {

        open("https://github.com/");
        $(".header-search-input").setValue(query.name());
        $(".header-search-input").pressEnter();
        $$(".v-align-middle").
                find(text(query.name()))
                .shouldBe(visible);

    }

    //4.CsvSource
    @ParameterizedTest(name = "Поиск слова {0} и проверка отображения текста {1}")
    @Tag("blocker")
    @CsvSource(value = {
            "Maven| maven",
            "Gradle| gradle"
    },
            delimiter = '|')
    void commonGithubCsvSourceSearchTest(String search, String result) {

        open("https://github.com/");
        $(".header-search-input").setValue(search);
        $(".header-search-input").pressEnter();
        $$(".v-align-middle").
                find(text(result))
                .shouldBe(visible);

    }

}
