package ge.tbc.testautomation.configuration;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Configuration.*;

public class TestConfig {

    protected SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        softAssert = new SoftAssert();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> passManagerSettings = new HashMap<>();
        options.addArguments("start-maximized");
        passManagerSettings.put("credentials_enable_service", false);
        passManagerSettings.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", passManagerSettings);
        browserCapabilities = options;
        browserSize = null;
        timeout = 6000;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
        softAssert.assertAll();
    }
}
