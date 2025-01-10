package ge.tbc.testautomation.saucedemo.steps;

import ge.tbc.testautomation.databaseconnection.DatabaseSteps;
import ge.tbc.testautomation.saucedemo.pages.LoginPage;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();
    DatabaseSteps databaseSteps = new DatabaseSteps();

    @Step("Fetch credentials for the standard user from the database")
    public String[] fetchCredentialsForStandard() {
        try (ResultSet rs = databaseSteps.getStandardUser()) {
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                return new String[]{username, password};
            } else {
                throw new RuntimeException("No credentials found for standard_user");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching credentials", e);
        }
    }

    @Step("Fetch credentials for the locked user from the database")
    public String[] fetchCredentialsForLocked() {
        try (ResultSet rs = databaseSteps.getLockedUser()) {
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                return new String[]{username, password};
            } else {
                throw new RuntimeException("No credentials found for locked_user");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching credentials", e);
        }
    }

    @Step("Fill the username field with the standard user credentials")
    public LoginSteps fillUsernameWithStandard() {
        String username = fetchCredentialsForStandard()[0];
        loginPage.userNameField.sendKeys(username);

        return this;
    }

    @Step("Fill the password field with the standard user credentials")
    public LoginSteps fillPassword() {
        String password = fetchCredentialsForStandard()[1];
        loginPage.passwordField.sendKeys(password);

        return this;
    }

    @Step("Click on the Login button")
    public LoginSteps clickOnLogin() {
        loginPage.loginButton.click();

        return this;
    }

    @Step("Fill the username field with the locked user credentials")
    public LoginSteps fillUsernameWithLocked() {
        String username = fetchCredentialsForLocked()[0];
        loginPage.userNameField.sendKeys(username);

        return this;
    }

    @Step("Validate the error message for locked user")
    public LoginSteps validateErrMessage() {
        loginPage.loginErrElement.shouldHave(text("Epic sadface: Sorry, this user has been locked out."));

        return this;
    }

    @Step("Validate the visibility of the error close button")
    public void validateVisibilityOfErrXBtn() {
        loginPage.loginErrElXButton.shouldBe(visible);

    }

    @Step("Validate that the username field is empty")
    public LoginSteps validateEmptinessOfLoginField() {
        Assert.assertTrue(Objects.requireNonNull(loginPage.userNameField.getValue()).isEmpty());

        return this;
    }

    @Step("Validate that the password field is empty")
    public void validateEmptinessOfPasswordField() {
        Assert.assertTrue(Objects.requireNonNull(loginPage.passwordField.getValue()).isEmpty());

    }
}
