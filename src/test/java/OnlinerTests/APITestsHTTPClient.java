package OnlinerTests;

import Entity.Cart;
import Utilities.HttpHelper;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APITestsHTTPClient extends HttpHelper {

    @Test(priority = 1)
    public void onlinerCatalog_Test() throws URISyntaxException, IOException, InterruptedException {
        //Fridge catalog
        JsonObject catalogObject = getObjFromGetResponse("https://catalog.onliner.by/sdapi/catalog.api/search/refrigerator?mfr[0]=samsung&color[0]=black");
        JsonObject firstCatalogObj = getFirstObjOfArray(catalogObject, "products");
        String productID = getStringValueFromObj(firstCatalogObj, "id");
        String productKey = getStringValueFromObj(firstCatalogObj, "key");
        String productName = getStringValueFromObj(firstCatalogObj, "name");
        String extendedName = getStringValueFromObj(firstCatalogObj, "extended_name");
        String description = getStringValueFromObj(firstCatalogObj, "description");
        Assert.assertTrue(extendedName.contains("Samsung"));
        Assert.assertTrue(description.contains("черный"));

        //position
        JsonObject positionsObject = getObjFromGetResponse("https://catalog.onliner.by/sdapi/shop.api/products/" + productKey + "/positions");
        JsonObject firstPositionsObject = getFirstObjOfArray(positionsObject.getAsJsonObject("positions"), "primary");
        String positionID = getStringValueFromObj(firstPositionsObject, "id");
        String shopID = getStringValueFromObj(firstPositionsObject, "shop_id");

        //cartID
        Cart cartBody = Cart.builder().position_id(positionID).product_id(productID).shop_id(shopID).quantity(1).product_key(productKey).build();
        HttpRequest cart = postRequest("https://catalog.onliner.by/sdapi/cart.api/detached-cart/add/", cartBody);
        HttpResponse<String> responseCart = getStringResponse(cart);
        JsonObject cartObject = getJsonObjFromResponse(responseCart);
        String cartID = getStringValueFromObj(cartObject, "cart_id");

        //Get cart
        JsonObject getBinObject = getObjFromGetResponse("https://catalog.onliner.by/sdapi/cart.api/v2/detached-cart/" + cartID);
        JsonObject firstProductObject = getFirstObjOfArray(getFirstObjOfArray(getBinObject, ("position_groups")), "positions")
                .get("product")
                .getAsJsonObject();

        String actualProductName = getStringValueFromObj(firstProductObject, "name");
        String actualExtendedName = getStringValueFromObj(firstProductObject, "extended_name");
        String actualDescription = getStringValueFromObj(firstProductObject, "description");

        Assert.assertEquals(actualProductName, productName);
        Assert.assertEquals(actualExtendedName, extendedName);
        Assert.assertEquals(actualDescription, description);
    }
}
