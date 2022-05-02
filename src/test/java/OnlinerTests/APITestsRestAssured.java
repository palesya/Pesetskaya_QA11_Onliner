package OnlinerTests;

import Entity.Cart;
import Utilities.RestAssuredHelper;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

public class APITestsRestAssured extends RestAssuredHelper {

    @BeforeTest
    public void precondition() {
        baseURI = ("https://catalog.onliner.by/sdapi");
    }

    @Test(priority = 1)
    public void onlinerCatalog_Test() {
        //catalog
        JsonPath jsonPathCatalog = getJsonPathFromGetRequest("/catalog.api/search/mobile?on_sale=1&mfr[0]=apple&group=1");
        String productID= getFirstValueFromJsonPath(jsonPathCatalog,"products.id");
        String productKey = getFirstValueFromJsonPath(jsonPathCatalog,"products.key");
        String productName = getFirstValueFromJsonPath(jsonPathCatalog,"products.name");

        //position
        JsonPath jsonPathPosition = getJsonPathFromGetRequest("/shop.api/products/"+ productKey + "/positions");
        String positionID= getFirstValueFromJsonPath(jsonPathPosition,"positions.primary.id");
        String shopID = getFirstValueFromJsonPath(jsonPathPosition,"positions.primary.shop_id");

        //cartID
        Cart cartBody = Cart.builder().position_id(positionID).product_id(productID).shop_id(shopID).quantity(1).product_key(productKey).build();
        JsonPath jsonPathCart = getJsonPathFromPutRequest("/cart.api/detached-cart/add/",cartBody);
        String cartID = jsonPathCart.get("cart_id").toString();

        //Get cart
        JsonPath jsonPathGetCart = getJsonPathFromGetRequest("https://catalog.onliner.by/sdapi/cart.api/v2/detached-cart/"+ cartID);
        String actualProductName=jsonPathGetCart.get("position_groups.positions.product.name").toString();
        Assert.assertTrue(actualProductName.contains(productName));
    }

}
