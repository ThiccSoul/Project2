package ge.tbc.testautomation.swoop.steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import ge.tbc.testautomation.swoop.pages.StaticHeaderPage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static ge.tbc.testautomation.data.Constants.*;

public class StaticHeaderSteps {
    StaticHeaderPage staticHeaderPage = new StaticHeaderPage();

    @Step("Fill search field with '{searchTerm}'")
    public StaticHeaderSteps searchFill(String searchTerm) {
        staticHeaderPage.searchField.setValue(searchTerm);
        return this;
    }

    @Step("Click on the search field")
    public StaticHeaderSteps clickOnSearch() {
        staticHeaderPage.searchField.click();
        return this;
    }

    @Step("Run search by pressing Enter")
    public void searchRun() {
        staticHeaderPage.searchField.sendKeys(Keys.ENTER);
    }

    @Step("Click on the category button")
    public StaticHeaderSteps clickOnCategory() {
        staticHeaderPage.categoryBtn.click();
        return this;
    }

    @Step("Hover over the 'Rest' category")
    public StaticHeaderSteps hoverOnRest() {
        staticHeaderPage.restCategory.hover();
        return this;
    }

    @Step("Click on the 'Eat' category")
    public void clickOnEat() {
        staticHeaderPage.eatCategory.click();
    }

    @Step("Click on the 'Mountain' button")
    public void clickOnMountain() {
        staticHeaderPage.mountain.click();
    }

    @Step("Click on 'Change Language' option")
    public StaticHeaderSteps clickOnChangeLang() {
        staticHeaderPage.langChange.click();
        return this;
    }

    @Step("wait until language is set to english")
    public void waitUntillLangIsEng() {
        Selenide.Wait().until(_ -> WebDriverRunner.url().contains(langUrl));
    }

    @Step("wait until language is set to english")
    public void waitUntillLangIsGeo() {
        Selenide.Wait().until(_ -> !WebDriverRunner.url().contains(langUrl));
    }

    @Step("Set language to Georgian")
    public StaticHeaderSteps setLangToGeo() {
        staticHeaderPage.geoLang.click();

        return this;
    }

    @Step("Set language to English")
    public StaticHeaderSteps setLangToEng() {
        staticHeaderPage.engLang.click();

        return this;
    }
}
