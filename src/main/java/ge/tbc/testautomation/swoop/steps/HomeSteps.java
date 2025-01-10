package ge.tbc.testautomation.swoop.steps;

import ge.tbc.testautomation.swoop.pages.SearchResultsPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.matchText;
import static ge.tbc.testautomation.data.Constants.engLangErrText;
import static ge.tbc.testautomation.data.Constants.geoLangErrText;
import static ge.tbc.testautomation.utilFunctions.Helpers.isTextInEnglish;
import static ge.tbc.testautomation.utilFunctions.Helpers.isTextInGeorgian;

public class HomeSteps {
    SearchResultsPage searchResultsPage = new SearchResultsPage();

    @Step("Validate that text is in English")
    public void validateTextIsInEnglish() {
        searchResultsPage.labelsForLangCheck.filterBy(matchText(".*\\p{L}.*")).forEach(element -> {
            String text = element.getText().trim();
            boolean isEnglish = isTextInEnglish(text);
            if (!isEnglish) {
                throw new IllegalArgumentException(engLangErrText);
            }
        });
    }


    @Step("Validate that text is in Georgian")
    public void validateTextIsInGeorgian() {
        searchResultsPage.labelsForLangCheck.filterBy(matchText(".*\\p{L}.*")).forEach(element -> {
            String text = element.getText().trim();
            boolean isGeorgian = isTextInGeorgian(text);
            if (!isGeorgian) {
                throw new IllegalArgumentException(geoLangErrText);
            }
        });
    }
}
