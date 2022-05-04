package PageObjects.Onliner;

import PageObjects.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Arrays;

public class RegisterPage extends BasePage {
    private By emailField = By.cssSelector("input[placeholder$=e-mail]");
    private By passwordField = By.cssSelector("[placeholder^='Придумайте']");
    private By passwordRepeat = By.cssSelector("[placeholder^='Повторите']");
    private By checkbox = By.cssSelector("span[class*='faux']");
    private By errorForPasswordLength = By.cssSelector("[class*='description_error']");
    private By passwordDescription = By.cssSelector("[class*='securebox'] [class*='primary']");
    private By submitButton = By.xpath("//div/*[@type='submit']");

    public RegisterPage enterPasswordAndRepeatPasswordWithSpaces() {
        String password = "            12";
        enter(passwordField, password);
        enter(passwordRepeat,password);
        return this;
    }

    public RegisterPage checkCheckbox() {
        clickButton(checkbox);
        return this;
    }

    public RegisterPage checkErrorForPasswordLength() {
        wait(errorForPasswordLength);
        compareErrorWithExpected(errorForPasswordLength, "errorForPasswordLength");
        return this;
    }

    public RegisterPage countSymbolsInPasswordMessage() {
        Object[] text = Arrays.stream(getWebElement(passwordDescription).getText().split(" ")).toArray();
        Object numberOfSymbolsInMessage = text[2];
        Assert.assertEquals(numberOfSymbolsInMessage, "2");
        return this;
    }

    public RegisterPage enterEmail(String property) {
        enterPropertyValueIntoField(emailField, property);
        log.debug("Email was entered.");
        return this;
    }

    public RegisterPage clickSubmitButton() throws InterruptedException {
        clickButton(submitButton);
        Thread.sleep(1000);
        log.debug("Submit button was clicked.");
        return this;
    }
    public RegisterPage screenshot() {
        screenshot("src/test/java/Screenshots");
        return this;
    }
}
