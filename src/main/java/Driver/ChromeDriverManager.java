package Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

import static Properties.PropertyReader.getProperties;

public class ChromeDriverManager extends DriverManager{
    @Override
    public void createDriver() {
        WebDriver webDriver;
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions=new ChromeOptions();
        if(getProperties().containsKey("chrome.options")){
            chromeOptions.addArguments(getProperties().getProperty("chromeOptions").split(","));
            webDriver=new ChromeDriver(chromeOptions);
        }else{
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("start-maximized");
            webDriver=new ChromeDriver(options);
        }
        driver.set(webDriver);
    }
}
