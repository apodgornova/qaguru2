package task2;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    void testDemoForm() {

        open("https://demoqa.com/automation-practice-form");

        //fill fields
        $("#firstName").setValue("Somefirstname");
        $("#lastName").setValue("Somelastname");
        $("#userEmail").setValue("some@email.com");
        $x("//*[contains(text(), 'Female')]").click();
        $("#userNumber").setValue("7123456789");
        Helpers.setDateById("dateOfBirthInput", "29 Sep 1980");
        $("#subjectsInput").setValue("Math, Programming");


    }


}
