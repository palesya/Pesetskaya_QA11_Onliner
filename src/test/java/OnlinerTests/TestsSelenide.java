package OnlinerTests;

import BaseObjects.SelenideBaseTest;
import Entity.User;
import PageObjects.OnlinerSelenide.CatalogPage;
import PageObjects.OnlinerSelenide.RegisterPage;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Log4j
public class TestsSelenide extends SelenideBaseTest {

    @Test(description = "Check allowed password length",
            dataProvider = "validUserCredentials")
    public void checkAllowedPasswordLength(User user) {
        get(CatalogPage.class).clickEnterButton()
                .goToRegistrationPage();
        get(RegisterPage.class).register(user)
                .checkSuccessRegistration();
    }

    @Test(description = "Check not allowed password length",
            dataProvider = "invalidUserCredentials")
    public void checkNotAllowedPasswordLength(User user) {
        get(CatalogPage.class).clickEnterButton()
                .goToRegistrationPage();
        get(RegisterPage.class).register(user)
                .checkErrorForPasswordLength();
    }

    @DataProvider(name = "validUserCredentials", parallel = true)
    public Object[][] validData() {
        return new Object[][]{
                {new User.UserBuilder().withEmail(getProperty("email")).withPassword("11111111").build()},
                {new User.UserBuilder().withEmail(getProperty("email"))
                        .withPassword("1111111111111111111111111111111111111111111111111111111111111111").build()}
        };
    }

    @DataProvider(name = "invalidUserCredentials", parallel = true)
    public Object[][] invalidData() {
        return new Object[][]{
                {new User.UserBuilder().withEmail("alesya@gmail.com").withPassword("1111111").build()},
                {new User.UserBuilder().withEmail(getProperty("email"))
                        .withPassword("11111111111111111111111111111111111111111111111111111111111111111").build()}
        };
    }


}
