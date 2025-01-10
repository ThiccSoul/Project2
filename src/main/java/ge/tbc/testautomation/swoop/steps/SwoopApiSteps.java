package ge.tbc.testautomation.swoop.steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;

import static ge.tbc.testautomation.data.Constants.*;

public class SwoopApiSteps {

    @Step("Send request to Swoop")
    public Response sendRequestToFetch(String filterTerm) {
        return RestAssured.given()
                .queryParam(apiKey1, filterTerm)
                .queryParam(apiKey2, apiKey2Value)
                .get(mainURL);
    }

    @Step("Validate that the response status code is {code}")
    public SwoopApiSteps validateResponseStatusCode(Response response, int code) {
        Assert.assertEquals(response.statusCode(), code);
        return this;
    }

    @Step("Validate that the offer IDs are not null or empty")
    public void validateOfferIdsNotNullOrEmpty(Response response) {
        JsonPath jsonPath = response.jsonPath();
        List<String> offerIds = jsonPath.getList("offers.id");

        for (String id : offerIds) {
            Assert.assertNotNull(id);
            Assert.assertFalse(id.isEmpty());
        }
    }
}
