package PageObjects.OnlinerSelenide;

import Entity.User;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;

import java.util.Arrays;

import static Properties.PropertyReader.getProperties;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

@Log4j
public class RegisterPage {
    SelenideElement emailField = $("input[placeholder$=e-mail]");
    SelenideElement passwordField = $("[placeholder^='Придумайте']");
    SelenideElement passwordRepeat = $("[placeholder^='Повторите']");
    SelenideElement checkbox = $("span[class*='faux']");
    SelenideElement errorForPasswordLength = $("[class*='description_error']");
    SelenideElement passwordDescription = $("[class*='securebox'] [class*='primary']");
    SelenideElement submitButton = $(byXpath("//div/*[@type='submit']"));
    SelenideElement goToGmailButton = $("[class*='auth-button']");


    public RegisterPage enterPasswordAndRepeatPassword(String password) {
        passwordField.sendKeys(password);
        passwordRepeat.sendKeys(password);
        log.debug("Password and RepeatPassword entered " + password);
        return this;
    }

    public RegisterPage checkCheckbox() {
        checkbox.click();
        log.debug("Checkbox was checked.");
        return this;
    }

    public RegisterPage checkErrorForPasswordLength() {
        errorForPasswordLength.shouldBe(Condition.visible);
        Assert.assertEquals(errorForPasswordLength.getText(), getProperties().getProperty("errorForPasswordLength"));
        log.debug("Error appeared " + errorForPasswordLength.getText());
        return this;
    }

    public RegisterPage countSymbolsInPasswordMessage() {
        Object[] text = Arrays.stream(passwordDescription.getText().split(" ")).toArray();
        Object numberOfSymbolsInMessage = text[2];
        Assert.assertEquals(numberOfSymbolsInMessage, "2");
        return this;
    }

    public RegisterPage enterEmail(String property) {
        emailField.sendKeys(property);
        log.debug("Email entered " + property);
        return this;
    }

    public RegisterPage clickSubmitButton() {
        submitButton.click();
        log.debug("Submit button was clicked.");
        return this;
    }

    public RegisterPage register(User user) {
        enterEmail(user.getEmail());
        enterPasswordAndRepeatPassword(user.getPassword());
        checkCheckbox();
        clickSubmitButton();
        return this;
    }

    public RegisterPage checkSuccessRegistration() {
        submitButton.shouldBe(Condition.disappear);
        goToGmailButton.shouldBe(Condition.appear);
        String actualText = goToGmailButton.getText();
        Assert.assertTrue(actualText.contains("Перейти в почту"));
        log.debug("Check success registration.");
        return this;
    }
}
