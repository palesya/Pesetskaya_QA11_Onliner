package CucumberSteps.Onliner;


import BaseObjects.BaseTest;
import Driver.ChromeDriverManager;
import PageObjects.Onliner.CatalogPage;
import PageObjects.Onliner.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Search_Steps extends BaseTest {

    @Given("i load catalog page")
    public void openCatalogPage() {
        ChromeDriverManager.getDriver();
        get(CatalogPage.class).open();
    }

    @When("i select house appliance")
    public void selectHouseAppliance() {
        get(CatalogPage.class).selectHouseAppliance();
    }

    @And("i select cleaning")
    public void selectCleaningOption() {
        get(CatalogPage.class).selectCleaning();
    }

    @And("i select vacuumcleaner")
    public void openVacuumcleaner() {
        get(CatalogPage.class).selectVacuumCleaner();
    }

    @And("i search for fire sign")
    public void searchFireSign() {
        get(ProductsPage.class).searchHotSign();
    }

    @Then("i click on fire sign")
    public void clickFireSign() {
        get(ProductsPage.class).clickHotSign();
    }

    @And("i check appeared hint text")
    public void checkHintText() {
        get(ProductsPage.class).checkHintText();
    }
}



