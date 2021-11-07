package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationsPage {

    // locators & elements
    private final String URL = "https://demoqa.com/automation-practice-form";
    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            phoneInput = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            fileInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateSelect = $x("//*[contains(text(), 'Select State')]"),
            stateFirstOption = $("#react-select-3-option-0"),
            citySelect = $x("//*[contains(text(), 'Select City')]"),
            cityFirstOption = $("#react-select-4-option-0"),
            saveRegistrationButton = $(".btn-primary"),
            resultsTable = $(".modal-body"),
            closeModalButton = $("#closeLargeModal");

    public CalendarComponent calendar = new CalendarComponent();

    // actions
    public RegistrationsPage openPage() {
        open(URL);
        return this;
    }

    public RegistrationsPage typeFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationsPage typeLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationsPage typeEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public RegistrationsPage chooseGender(String value) {

        $x("//*[contains(text(), '" + value + "')]").click();
        return this;
    }

    public RegistrationsPage typePhone(String value) {
        phoneInput.setValue(value);
        return this;
    }

    public RegistrationsPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationsPage setHobby(String value) {
        $x("//*[contains(text(), '" + value + "')]").click();
        return this;
    }

    public RegistrationsPage addPhotoFile(File file) {

        fileInput.uploadFile(file);
        return this;
    }

    public RegistrationsPage typeAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    public RegistrationsPage setFirstState() {
        stateSelect.click();
        stateFirstOption.click();
        return this;
    }

    public RegistrationsPage setFirstCity() {
        citySelect.click();
        cityFirstOption.click();
        return this;
    }

    public RegistrationsPage saveRegistration() {
        saveRegistrationButton.click();
        return this;
    }

    public RegistrationsPage closeModal() {
        closeModalButton.click();
        return this;
    }

    public RegistrationsPage checkResultsValue(String key, String value) {
        resultsTable.shouldHave(text(key), text(value));
        return this;
    }

}
