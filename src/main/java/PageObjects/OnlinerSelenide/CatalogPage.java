package PageObjects.OnlinerSelenide;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

@Log4j
public class CatalogPage {
    SelenideElement enterButton = $("div[class$=text]");
    SelenideElement registrationButton = $("[href$='registration']");

    public CatalogPage clickEnterButton() {
        enterButton.click();
        log.debug("Enter button was clicked.");
        return this;
    }

    public CatalogPage goToRegistrationPage() {
        registrationButton.click();
        webdriver().shouldHave(url("https://profile.onliner.by/registration"));
        log.debug("Registration page is opened.");
        return this;
    }
}
