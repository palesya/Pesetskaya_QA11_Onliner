package PageObjects.Onliner;

import PageObjects.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Arrays;


public class CartPage extends BasePage {
    private By numberOfItems = By.cssSelector("[class$='ammount']");
    private By removeButton = By.cssSelector("div[class$='remove'] div a");
    private By description = By.cssSelector("div[class*='complementary']");
    private By messageAfterRemove = By.cssSelector("[class$='extra']");
    private By links = By.cssSelector("[class*='link_small']");
    private By cartMessage = By.cssSelector("[class*='cart-message__title']");

    public CartPage clickRemoveButton() {
        log.debug("Click Remove button");
        moveToElementAndClick(description);
        pause(1);
        moveToElementAndClick(removeButton);
        return this;
    }

    public CartPage checkMessageAfterRemove() {
        Assert.assertTrue(getText(messageAfterRemove).contains(getProperty("messageAfterRemove")));
        return this;
    }

    public CartPage checkNumberOfAddedItems(String expectedNumber) {
        Object[] text = Arrays.stream(getText(numberOfItems).split(" ")).toArray();
        Assert.assertEquals(text[0].toString(), expectedNumber);
        return this;
    }

    public CartPage clickClose() {
        driver.findElements(links).get(1).click();
        log.debug("Click close link");
        return this;
    }

    public CartPage checkCartMessage() {
        compareTextWithExpected(cartMessage, "messageFromCart");
        return this;
    }

}
