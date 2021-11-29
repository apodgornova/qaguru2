package task2;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTest extends pages.TestBase{

    File picOk = new File("src/test/resources/interesting-cat-facts.jpg");
    String fileName = "interesting-cat-facts.jpg";
    String URL = "https://demoqa.com/automation-practice-form";
    String firstName = "Somefirstname";
    String lastName = "Somelastname";
    String email = "some@email.com";
    String gender = "Female";
    String phone = "7123456789";
    String month = "September";
    String year = "1980";
    String day = "29";
    String subject1 = "Maths";
    String subject2 = "Computer Science";
    String hobby = "Music";
    String address = "Some Area Some City Street st.1 fl.1";
    String state = "NCR";
    String city = "Delhi";

    @Test
    void testDemoForm() {

        System.out.println("URL is"+ URL);
        open(URL);


        //fill fields
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $x("//*[contains(text(), '" + gender + "')]").click();
        $("#userNumber").setValue(phone);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText(month);
        $(".react-datepicker__year-select").selectOptionContainingText(year);
        $(".react-datepicker__day--029").click();
        $("#subjectsInput").setValue(subject1).pressEnter();
        $("#subjectsInput").setValue(subject2).pressEnter();
        $x("//*[contains(text(), '" + hobby + "')]").click();
        $("#uploadPicture").uploadFile(picOk);
        $("#currentAddress").setValue(address);
        $x("//*[contains(text(), 'Select State')]").click();
        $("#react-select-3-option-0").click();
        $x("//*[contains(text(), 'Select City')]").click();
        $("#react-select-4-option-0").click();
        $(".btn-primary").click();

        //verify filled values
        $(".modal-body").shouldHave(text("Student name"), text(firstName + " " + lastName),
                text("Student Email"), text(email),
                text("Gender"), text(gender),
                text("Mobile"), text(phone),
                text("Date of Birth"), text(day + " " + month + "," + year),
                text("Subjects"), text(subject1 + ", " + subject2),
                text("Hobbies"), text(hobby),
                text("Picture"), text(fileName),
                text("Address"), text(address),
                text("State and City"), text(state + " " + city));

        //close modal form
        $("#closeLargeModal").click();

    }

}
