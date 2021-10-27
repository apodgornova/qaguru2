package task2;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTest {

    File picOk = new File("src/test/resources/interesting-cat-facts.jpg");

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
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $x("//*[contains(text(), 'Music')]").click();
        $("#uploadPicture").uploadFile(picOk);
        $("#currentAddress").setValue("Some Area Some City Street st.1 fl.1");
        $x("//*[contains(text(), 'Select State')]").click();
        $("#react-select-3-option-1").click();
        $x("//*[contains(text(), 'Select City')]").click();
        $("#react-select-4-option-1").click();
        $(".btn-primary").click();

        //verify filled values



    }


}
