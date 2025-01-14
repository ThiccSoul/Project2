package ge.tbc.testautomation.saucedemo.steps;

import ge.tbc.testautomation.saucedemo.pages.HomePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class HomeSteps {
    HomePage homePage = new HomePage();

    @Step("Validate that all images on the homepage are visible")
    public void validateImages() {
        homePage.imgElements.forEach(el -> {
            el.shouldBe(visible);
        });

    }

    @Step("Click on the burger menu")
    public HomeSteps clickingOnBurgerMenu() {
        homePage.burgerMenu.click();

        return this;
    }

    @Step("Click on the logout button")
    public void clickingOnLogout() {
        homePage.logoutElement.shouldBe(visible);
        homePage.logoutElement.click();
    }
}
