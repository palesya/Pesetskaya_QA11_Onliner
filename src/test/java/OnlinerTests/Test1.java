package OnlinerTests;

import BaseObjects.BaseTest;
import PageObjects.Onliner.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test1 extends BaseTest {

    @BeforeMethod
    public void openCatalogPage() {
        get(CatalogPage.class).open();
    }

    @Test(description = "Check if Catalog page is opened", priority = 1)
    public void checkCatalogPage() {
        get(CatalogPage.class).clickEnterButton();
        get(LoginPage.class).checkAuthenticationFormIsOpened();
    }

    @Test(description = "Check error appears when login with empty email", priority = 2, dependsOnMethods = "checkCatalogPage")
    public void checkLoginWithEmptyName() {
        get(CatalogPage.class).clickEnterButton();
        get(LoginPage.class).enterPassword("password")
                .clickSubmitButton()
                .checkErrorForEmptyEmail();
    }

    @Test(description = "Check hint text for fire sign", priority = 3)
    public void checkHint() {
        get(CatalogPage.class).selectHouseAppliance()
                .selectCleaning()
                .selectVacuumCleaner();
        get(ProductsPage.class).clickHotSign()
                .clickHotSign()
                .checkHintText();
    }

    @Test(description = "Check dialog window", priority = 4)
    public void checkDialogWindow() {
        get(CatalogPage.class).selectHouseAppliance()
                .selectCleaning()
                .selectVacuumCleaner();
        get(ProductsPage.class).checkDialogWindowAvailable()
                .checkButtonNamesOnDialogWindow();
    }

    @Test(description = "Check adding to cart", priority = 5)
    public void checkAddToCart() {
        get(CatalogPage.class).selectHouseAppliance()
                .selectCleaning()
                .selectVacuumCleaner()
                .selectItem()
                .addToCart()
                .goToCart();
        get(CartPage.class).checkNumberOfAddedItems("1");
    }

    @Test(description = "Check remove from cart", priority = 6, dependsOnMethods = "checkAddToCart")
    public void checkRemoveFromCart() {
        get(CatalogPage.class).clickCartButton();
        get(CartPage.class).clickRemoveButton()
                .checkMessageAfterRemove();
    }

    @Test(description = "Check registration with password with spaces at the beginning/end", priority = 7)
    public void checkPasswordWithSpaces() {
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
