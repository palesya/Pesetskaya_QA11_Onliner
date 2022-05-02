package OnlinerTests;

import BaseObjects.BaseTest;
import PageObjects.Onliner.ForumPage;
import PageObjects.Yandex.SearchPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Upload extends BaseTest {

   @BeforeMethod
    public void openYandexPage() {
        get(SearchPage.class).open();
    }

    @Test(description = "Check if Catalog page is opened", priority = 1)
    public void checkCatalogPage() throws InterruptedException {
        get(SearchPage.class).uploadPicture()
                .checkIfLinkIsFound()
                        .goToNewlyOpenedTab();
        get(ForumPage.class).goToCatalogPage();
        System.out.println("Hello");
    }

}



