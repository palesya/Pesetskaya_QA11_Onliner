package Utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class HttpHelper {

    private HttpRequest getRequest(String URI) throws URISyntaxException {
       return HttpRequest
                .newBuilder()
                .uri(new URI(URI))
                .GET().build();
    }

    protected HttpRequest postRequest(String uri, Object object) throws URISyntaxException {
        Gson gson = new Gson();
        return HttpRequest
                .newBuilder()
                .uri(new URI(uri))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(object)))
                .build();
    }

    protected JsonObject getJsonObjFromResponse(HttpResponse<String> response) {
        Gson gson = new Gson();
        return gson.fromJson(response.body(), JsonObject.class);
    }

    protected JsonObject getFirstObjOfArray(JsonObject object, String member) {
        return object.getAsJsonArray(member).get(0).getAsJsonObject();
    }

    protected String getStringValueFromObj(JsonObject object, String member) {
        return object.get(member).getAsString();
    }

    protected HttpResponse<String> getStringResponse(HttpRequest request) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    protected JsonObject getObjFromGetResponse(String uri) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = getRequest(uri);
        HttpResponse<String> responseCatalog = getStringResponse(httpRequest);
        return getJsonObjFromResponse(responseCatalog);
    }



}
