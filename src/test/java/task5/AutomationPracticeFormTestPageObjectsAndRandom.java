package task5;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static task5.TestData.*;

public class AutomationPracticeFormTestPageObjectsAndRandom extends TestBase {

    Faker faker = new Faker(new Locale("ru"));
    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("ru"), new RandomService());

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().safeEmailAddress();
    String phone = "7" + fakeValuesService.regexify("[0-9]{9}");
    String address = faker.address().streetAddress();

    @Test
    void testDemoForm() {

        registrationsPage.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .chooseGender(GENDER)
                .typePhone(phone);

        registrationsPage.calendar.setDate(DAY, MONTH, YEAR);

        registrationsPage.setSubject(SUBJECT_1)
                .setSubject(SUBJECT_2)
                .setHobby(HOBBY)
                .addPhotoFile(PIC_OK)
                .typeAddress(address)
                .setFirstState()
                .setFirstCity()
                .saveRegistration();

        registrationsPage.checkResultsValue("Student name", firstName + " " + lastName)
                .checkResultsValue("Student Email", email)
                .checkResultsValue("Gender", GENDER)
                .checkResultsValue("Mobile", phone)
                .checkResultsValue("Date of Birth", DAY + " " + MONTH + "," + YEAR)
                .checkResultsValue("Subjects", SUBJECT_1 + ", " + SUBJECT_2)
                .checkResultsValue("Hobbies", HOBBY)
                .checkResultsValue("Picture", FILE_NAME)
                .checkResultsValue("Address", address)
                .checkResultsValue("State and City", STATE + " " + CITY);

        registrationsPage.closeModal();

    }

}
