package task2;

import com.codeborne.selenide.Condition;
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
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("September");
        $(".react-datepicker__year-select").selectOptionContainingText("1980");
        $(".react-datepicker__day--029").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $x("//*[contains(text(), 'Music')]").click();
        $("#uploadPicture").uploadFile(picOk);
        $("#currentAddress").setValue("Some Area Some City Street st.1 fl.1");
        $x("//*[contains(text(), 'Select State')]").click();
        $("#react-select-3-option-0").click();
        $x("//*[contains(text(), 'Select City')]").click();
        $("#react-select-4-option-0").click();
        $(".btn-primary").click();

        //verify filled values
        $("tr:nth-child(1) > td:nth-child(2)").shouldHave(Condition.text("Somefirstname Somelastname"));
        $("tr:nth-child(2) > td:nth-child(2)").shouldHave(Condition.text("some@email.com"));
        $("tr:nth-child(3) > td:nth-child(2)").shouldHave(Condition.text("Female"));
        $("tr:nth-child(4) > td:nth-child(2)").shouldHave(Condition.text("7123456789"));
        $("tr:nth-child(5) > td:nth-child(2)").shouldHave(Condition.text("29 September,1980"));
        $("tr:nth-child(6) > td:nth-child(2)").shouldHave(Condition.text("Maths, Computer Science"));
        $("tr:nth-child(7) > td:nth-child(2)").shouldHave(Condition.text("Music"));
        $("tr:nth-child(8) > td:nth-child(2)").shouldHave(Condition.text("interesting-cat-facts.jpg"));
        $("tr:nth-child(9) > td:nth-child(2)").shouldHave(Condition.text("Some Area Some City Street st.1 fl.1"));
        $("tr:nth-child(10) > td:nth-child(2)").shouldHave(Condition.text("NCR Delhi"));
        $("#closeLargeModal").click();


    }


}
