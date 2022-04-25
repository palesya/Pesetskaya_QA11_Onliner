package PageObjects.Onliner;

import PageObjects.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ProductsPage extends BasePage {
    private By hotSign = By.cssSelector("[class$=hot]");
    private By hintForFire = By.cssSelector("[class$=line] [class$=content]");
    private By dialogWindow = By.cssSelector("[class$='delivery'] [class$='content']");
    private By buttonOnDialogWindow = By.cssSelector("[class$='delivery'] [class$='content'] [class*='button']");
    private By linkOnDialogWindow = By.cssSelector("[class$='delivery'] [class$='content'] [class*='link']");

    public ProductsPage searchHotSign() {
        isElementExists(hotSign);
        return this;
    }

    public ProductsPage clickHotSign() {
        clickButton(hotSign);
        return this;
    }

    public ProductsPage checkHintText() {
        String actualHintText = driver.findElement(with(hintForFire).below(hotSign)).getText();
        Assert.assertTrue(actualHintText.contains(getProperty("fireHint")));
        Assert.assertTrue(actualHintText.contains(getProperty("fireHintStrong")));
        log.debug("Check hint for fire sign.");
        return this;
    }

    public ProductsPage checkDialogWindowAvailable() {
        Assert.assertTrue(isElementExists(dialogWindow));
        log.debug("Check dialog window is available.");
        return this;
    }

    public ProductsPage checkButtonNamesOnDialogWindow() {
        Assert.assertEquals(getText(linkOnDialogWindow).trim(), getProperty("linkName"));
        Assert.assertEquals(getText(buttonOnDialogWindow).trim(), getProperty("ButtonName"));
        log.debug("Check button names on dialog window.");
        return this;
    }

}
