package Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager extends DriverManager{
    @Override
    public void createDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options=new FirefoxOptions();
        options.addArguments("--start-fullscreen");
        WebDriver webDriver=new FirefoxDriver(options);
        webDriver.manage().window().maximize();
        driver.set(webDriver);
    }
}
