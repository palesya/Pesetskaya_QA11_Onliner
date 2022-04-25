package BaseObjects;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;

import static io.github.bonigarcia.wdm.WebDriverManager.getInstance;
import static io.github.bonigarcia.wdm.config.DriverManagerType.valueOf;

public class DriverCreation {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void createDriver(String drivers) {
        WebDriver webDriver;
        if (driver.get() == null) {
            if (valueOf(drivers.toUpperCase(Locale.ROOT)) == DriverManagerType.valueOf("CHROME")) {
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                webDriver = new ChromeDriver(options);
            } else {
                webDriver = getInstance(valueOf(drivers.toUpperCase(Locale.ROOT))).create();
            }
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.set(webDriver);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().close();
            driver.get().quit();
            driver.remove();
        }
    }
}
