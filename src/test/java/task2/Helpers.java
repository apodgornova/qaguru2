package task2;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class Helpers {

    static void setDateById(String id, String date) {
        executeJavaScript(
                String.format("$('[id=\"%s\"]').val('%s')",
                        id, date)
        );
    }
}
