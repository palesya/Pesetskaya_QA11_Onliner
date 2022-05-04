package PageObjects.Onliner;

import PageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class CatalogPage extends BasePage {
    private By enterButton = By.cssSelector("div[class$=text]");
    private By houseAppliance = By.xpath("//li[@data-id='3']");
    private By cleaning = By.xpath("//*[contains(text(),'Уборка')]");
    private By vacuumcleaner = By.cssSelector("[class*='dropdown'] [href$='vacuumcleaner']");
    private By item = By.cssSelector("[class*=product] [class*=image]");
    private By addToCartButton = By.xpath("//*[contains(text(),'В корзину')]");
    private By goToCartButton = By.cssSelector("[class*='control'] [class$='recommended__button']");
    private By cartButton = By.cssSelector("[title='Корзина']");
    private By currency = By.cssSelector("a[class$='link'][href*='kurs']");
    private By registrationButton = By.cssSelector("[href$='registration']");

    public CatalogPage clickEnterButton() {
        clickButton(enterButton);
        return this;
    }

    public CatalogPage selectHouseAppliance() {
        clickButton(houseAppliance);
        return this;
    }

    public CatalogPage selectCleaning() {
        wait(cleaning);
        clickButton(cleaning);
        return this;
    }

    public CatalogPage selectVacuumCleaner() {
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(vacuumcleaner));
        clickButton(vacuumcleaner);
        return this;
    }

    public CatalogPage selectItem() {
        clickButton(item);
        return this;
    }

    public CatalogPage addToCart() {
        clickButton(addToCartButton);
        pause(2);
        return this;
    }

    public CatalogPage goToCart() {
        clickButton(goToCartButton);
        compareCurrentUrlWithExpected("https://cart.onliner.by/");
        return this;
    }

    public CatalogPage clickCartButton() {
        clickButton(cartButton);
        compareCurrentUrlWithExpected("https://cart.onliner.by/");
        return this;
    }

    public CatalogPage goToRegistrationPage() {
        clickButton(registrationButton);
        compareCurrentUrlWithExpected("https://profile.onliner.by/registration");
        return this;
    }

}
