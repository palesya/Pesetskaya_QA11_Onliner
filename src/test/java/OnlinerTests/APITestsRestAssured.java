package OnlinerTests;

import Entity.Cart;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class APITestsRestAssured {

    @BeforeTest
    public void precondition() {
        baseURI = ("https://catalog.onliner.by/sdapi");
    }

    @Test(priority = 1)
    public void onlinerCatalog_Test() {
        //catalog
        Response responseCatalog = given().get("/catalog.api/search/mobile?on_sale=1&mfr[0]=apple&group=1");
        responseCatalog.then().assertThat().statusCode(200);
        JsonPath jsonPathCatalog = responseCatalog.jsonPath();
        String productID= jsonPathCatalog.getList("products.id").get(0).toString();
        String productKey = jsonPathCatalog.getList("products.key").get(0).toString();
        String productName = jsonPathCatalog.getList("products.name").get(0).toString();

        //position
        Response responsePosition = given().get("/shop.api/products/"+ productKey + "/positions");
        responsePosition.then().assertThat().statusCode(200);
        JsonPath jsonPathPosition = responsePosition.jsonPath();
        String positionID= jsonPathPosition.getList("positions.primary.id").get(0).toString();
        String shopID = jsonPathPosition.getList("positions.primary.shop_id").get(0).toString();

        //cartID
        Cart cartBody = Cart.builder().position_id(positionID).product_id(productID).shop_id(shopID).quantity(1).product_key(productKey).build();
        Gson gson = new Gson();
        String cartBodyString = gson.toJson(cartBody);
        Response responseCart = given().when().header("Content-Type", "application/json").and().body(cartBodyString).post("/cart.api/detached-cart/add/");
        responseCart.then().assertThat().statusCode(200);
        JsonPath jsonPathCart = responseCart.jsonPath();
        String cartID = jsonPathCart.get("cart_id").toString();

        //Get cart
        Response responseGetCart = given().get("https://catalog.onliner.by/sdapi/cart.api/v2/detached-cart/" + cartID);
        responseGetCart.then().assertThat().statusCode(200);
        JsonPath jsonPathGetCart = responseGetCart.jsonPath();
        String actualProductName=jsonPathGetCart.get("position_groups.positions.product.name").toString();
        Assert.assertTrue(actualProductName.contains(productName));
    }

}
