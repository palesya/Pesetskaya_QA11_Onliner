package PageObjects.Onliner;

import PageObjects.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends BasePage {
    private By authenticationField = By.cssSelector("[class='auth-entry']");
    private By email = By.cssSelector("input[placeholder$=e-mail]");
    private By submitButton = By.xpath("//div/*[@type='submit']");
    private By password = By.cssSelector("[type=password]");
    private By errorWhenEmptyEmail = By.cssSelector("[class*=description_error]");

    public LoginPage checkAuthenticationFormIsOpened() {
        Assert.assertTrue(isElementExists(authenticationField));
        log.debug("Authentication form is opened.");
        return this;
    }
    public LoginPage enterPassword(String property) {
        enterPropertyValueIntoField(password, property);
        log.debug("Password was entered.");
        return this;
    }

    public LoginPage enterEmail(String property) {
        enterPropertyValueIntoField(email, property);
        log.debug("Email was entered.");
        return this;
    }

    public LoginPage checkErrorForEmptyEmail() {
        Assert.assertTrue(isElementExists(errorWhenEmptyEmail));
        compareErrorWithExpected(errorWhenEmptyEmail, "errorForEmptyEmail");
        log.debug("Check error for empty email.");
        return this;
    }

    public LoginPage clickSubmitButton() {
        clickButton(submitButton);
        log.debug("Submit button was clicked.");
        return this;
    }
}
