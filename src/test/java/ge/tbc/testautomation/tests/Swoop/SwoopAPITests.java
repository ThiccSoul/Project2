package ge.tbc.testautomation.tests.Swoop;

import ge.tbc.testautomation.configuration.TestConfig;
import ge.tbc.testautomation.swoop.steps.SwoopApiSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;

@Epic("Api Test Suite")
public class SwoopAPITests extends TestConfig {
    SwoopApiSteps swoopApiSteps = new SwoopApiSteps();

    @Test
    @Feature("Search Functionality")
    @Story("As a user, I want to search a destination")
    @Severity(SeverityLevel.CRITICAL)
    public void restTest() {
        Response response = swoopApiSteps
                .sendRequestToFetch(apiKey1Value);
        swoopApiSteps
                .validateResponseStatusCode(response, expectedStatusCode)
                .validateOfferIdsNotNullOrEmpty(response);
    }
}

