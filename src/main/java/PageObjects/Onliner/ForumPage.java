package PageObjects.Onliner;

import PageObjects.BasePage;
import org.openqa.selenium.By;

public class ForumPage extends BasePage {

    private By catalogButton = By.cssSelector("[href$='catalog.onliner.by']");

    public ForumPage goToCatalogPage() {
        clickButton(catalogButton);
        return this;
    }
}
