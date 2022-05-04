package OnlinerTests;

import BaseObjects.BaseTest;
import PageObjects.Onliner.CatalogPage;
import PageObjects.Onliner.RegisterPage;
import org.testng.annotations.Test;

public class TestWithIssue extends BaseTest {
    @Test(description = "Check registration with password with spaces at the beginning/end", priority = 7)
    public void checkPasswordWithSpaces() throws InterruptedException {
        get(CatalogPage.class).open();
        get(CatalogPage.class).clickEnterButton()
                .goToRegistrationPage();
        get(RegisterPage.class)
                .enterEmail("email")
                .enterPasswordAndRepeatPasswordWithSpaces()
                .checkCheckbox()
                .clickSubmitButton()
                .checkErrorForPasswordLength()
                .screenshot()
                .countSymbolsInPasswordMessage();
    }
}
