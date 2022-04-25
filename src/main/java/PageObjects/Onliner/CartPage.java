package PageObjects.Onliner;

import PageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.Arrays;

public class CartPage extends BasePage {
    private By numberOfItems = By.cssSelector("[class$='ammount']");
    private By removeButton = By.cssSelector("div[class$='remove'] div a");
    private By messageAfterRemove = By.cssSelector("[class$='extra']");

    public CartPage clickRemoveButton() {
        Actions actions=new Actions(driver);
        actions.moveToElement(getWebElement(removeButton)).perform();
        actions.click().perform();
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
}
