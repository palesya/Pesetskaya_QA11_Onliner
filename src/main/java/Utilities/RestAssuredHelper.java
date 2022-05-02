package Utilities;

import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public abstract class RestAssuredHelper {

    protected JsonPath getJsonPathFromGetRequest(String uri) {
        Response responseCatalog = given().get(uri);
        checkStatusCodeOK(responseCatalog);
        return responseCatalog.jsonPath();
    }

    protected String getFirstValueFromJsonPath(JsonPath jsonPath, String stringPath) {
        return jsonPath.getList(stringPath).get(0).toString();
    }

    protected JsonPath getJsonPathFromPutRequest(String uri, Object object) {
        Gson gson = new Gson();
        String objectString = gson.toJson(object);
        Response responseCart = given().when().header("Content-Type", "application/json").and().body(objectString).post(uri);
        checkStatusCodeOK(responseCart);
        return responseCart.jsonPath();
    }

    private void checkStatusCodeOK(Response response) {
        response.then().assertThat().statusCode(200);
    }

}
