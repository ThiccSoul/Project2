package ge.tbc.testautomation.tests.SauceDemo;

import ge.tbc.testautomation.configuration.TestConfig;
import ge.tbc.testautomation.saucedemo.steps.HomeSteps;
import ge.tbc.testautomation.saucedemo.steps.LoginSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

@Test(groups = "SauceDemoLogin")
@Epic("SauceDemo Test Suite")
public class SauceDemoTests extends TestConfig {

    LoginSteps loginSteps = new LoginSteps();
    HomeSteps homeSteps = new HomeSteps();

    @BeforeMethod
    public void startUp() {
        open("https://www.saucedemo.com/");
    }

    @Test
    @Feature("Login Functionality")
    @Story("As a user, I want to log in successfully using valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void successfulLoginTest() {
        loginSteps
                .fetchCredentialsForStandard();
        loginSteps
                .fillUsernameWithStandard()
                .fillPassword()
                .clickOnLogin();
        homeSteps
                .validateImages();
    }

    @Test
    @Feature("Login Functionality")
    @Story("As a user, I should see an error when logging in with a locked account")
    @Severity(SeverityLevel.CRITICAL)
    public void bannedUserLoginTest() {
        loginSteps
                .fetchCredentialsForLocked();
        loginSteps
                .fillUsernameWithLocked()
                .fillPassword()
                .clickOnLogin()
                .validateErrMessage()
                .validateVisibilityOfErrXBtn();

    }

    @Test
    @Feature("Logout Functionality")
    @Story("As a user, I want to log out successfully and clear input fields")
    @Severity(SeverityLevel.CRITICAL)
    public void logOutTest() {
        loginSteps
                .fetchCredentialsForStandard();
        loginSteps
                .fillUsernameWithStandard()
                .fillPassword()
                .clickOnLogin();
        homeSteps
                .clickingOnBurgerMenu()
                .clickingOnLogout();
        loginSteps
                .validateEmptinessOfLoginField()
                .validateEmptinessOfPasswordField();
    }
}
