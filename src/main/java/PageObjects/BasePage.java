package PageObjects;

import Properties.PropertyReader;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static BaseObjects.DriverCreation.getDriver;

public abstract class BasePage {
    public WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected Properties properties;
    protected Logger log = Logger.getLogger(BasePage.class);

    protected BasePage() {
        this.driver = getDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        this.actions = new Actions(this.driver);
        this.properties = PropertyReader.getProperties();
    }

    public BasePage open() {
        String url = properties.getProperty("url");
        log.debug("Open page " + url);
        driver.get(url);
        return this;
    }

    protected WebElement getWebElement(By element) {
        return driver.findElement(element);
    }

    private BasePage click(By element) {
        getWebElement(element).click();
        return this;
    }

    protected BasePage clickButton(By element) {
        log.debug("Click on button " + element);
        click(element);
        return this;
    }

    public Boolean isElementExists(By element) {
        log.debug("Is element exist: " + element);
        List<WebElement> elementList = driver.findElements(element);
        return elementList.size() > 0;
    }

    public BasePage enterPropertyValueIntoField(By field, String property) {
        String propertyValue = properties.getProperty(property);
        getWebElement(field).sendKeys(propertyValue);
        return this;
    }

    public String getProperty(String property) {
        return properties.getProperty(property);
    }

    protected String getText(By element) {
        log.debug("Get text of element: " + element);
        return getWebElement(element).getText();
    }

    protected BasePage moveCursorToRight(int numberOfMove) {
        Actions action = new Actions(driver);
        action.moveByOffset(numberOfMove, 0).perform();
        return this;
    }

    public BasePage compareCurrentUrlWithExpected(String expectedUrl) {
        log.debug("Compare current url " + driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        return this;
    }

    public BasePage compareErrorWithExpected(By field, String expectedError) {
        log.debug("Compare error " + getWebElement(field).getText().trim());
        Assert.assertEquals(getWebElement(field).getText().trim(), getProperty(expectedError));
        return this;
    }

    protected BasePage wait(By element) {
        log.debug("Wait for " + element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(getWebElement(element)));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        return this;
    }

    protected BasePage enter(By element, CharSequence... data) {
        log.debug("Enter " + Arrays.toString(data));
        getWebElement(element).sendKeys(data);
        return this;
    }

    protected BasePage screenshot(String path) {
        log.debug("Take screenshot to directory: " + path);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(path+"/screen.png");
        try {
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
