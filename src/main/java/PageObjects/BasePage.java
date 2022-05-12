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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import static Driver.DriverManager.getDriver;

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

    protected BasePage click(By element) {
        log.debug("Click on element " + element);
        try{
        getWebElement(element).click();}
        catch (WebDriverException e){
            e.printStackTrace();
        }
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

    public BasePage compareCurrentUrlWithExpected(String expectedUrl) {
        log.debug("Compare current url " + driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        return this;
    }

    public BasePage compareTextWithExpected(By field, String expectedError) {
        log.debug("Compare text " + getWebElement(field).getText().trim());
        Assert.assertEquals(getWebElement(field).getText().trim(), getProperty(expectedError));
        return this;
    }

    protected BasePage wait(By element) {
        log.debug("Wait for " + element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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
        String name=generateName();
        File destFile = new File(path+"/"+name+".png");
        try {
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    private String generateName(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyy-HH-mm-ss");
        LocalDateTime localDateTime=LocalDateTime.now();
        return dtf.format(localDateTime);
    }

    public BasePage scrollDown() {
        log.debug("Scroll Down");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
        return this;
    }

    public void goToNewlyOpenedTab() {
        log.debug("Switch to new tab");
        ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(1));
    }

    protected void pause(long seconds) {
        log.debug("Pause for seconds: " + seconds);
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void moveToElementAndClick(By element) {
        log.debug("Move to element amd click"+element);
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(element)).click().build().perform();
    }
}
