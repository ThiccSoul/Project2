package ge.tbc.testautomation.swoop.steps;

import ge.tbc.testautomation.configuration.TestConfig;
import ge.tbc.testautomation.swoop.pages.SearchResultsPage;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.*;
import static ge.tbc.testautomation.data.Constants.*;
import static ge.tbc.testautomation.utilFunctions.Helpers.*;

public class SearchResultsSteps extends TestConfig {
    SearchResultsPage searchResultsPage = new SearchResultsPage();
    List<String> cardOffers = new ArrayList<>();

    @Step("Assert that the search result matches the term '{searchTerm}'")
    public void searchAssert(String searchTerm) {
        searchResultsPage.categoryTitle.shouldBe(visible);
        searchResultsPage.categoryTitle.shouldHave(text(searchTerm));
    }

    @Step("Validate search error message visibility")
    public void notFoundAssert(Boolean errMsgExist) {
        if (errMsgExist) {
            searchResultsPage.searchErrorMsg.shouldHave(text(searchErrText));
        } else {
            searchResultsPage.searchErrorMsg.shouldNotBe(visible);
        }
    }

    @Step("Verify if category card exists")
    public boolean categoryAssert() {
        return !searchResultsPage.card.exists();
    }

    @Step("Wait for the page navigation buttons to become visible")
    public SearchResultsSteps waitForPageBtn() {
        searchResultsPage.pageBtns.first().shouldBe(visible);
        return this;
    }

    @Step("Iterate through pages and save card titles")
    public SearchResultsSteps iteratePagesAndSaveCardTexts() {
        for (int i = 0; i <= searchResultsPage.pageBtns.size(); i++) {
            cardOffers.addAll(searchResultsPage.cardTitles.texts());
            if (i < searchResultsPage.pageBtns.size()) {
                searchResultsPage.pageBtns.get(i).click();
                waitForPageBtn();
            }
        }
        return this;
    }

    @Step("Assert that all card titles are unique")
    public void assertThatCardTitlesAreUnique() {
        Assert.assertTrue(isUniqueTextList(cardOffers));
    }

    @Step("Click on the fifth page button")
    public SearchResultsSteps clickingOnFifthPageBtn() {
        searchResultsPage.pageBtns.forEach(el -> {
            el.shouldBe(visible);
        });
        searchResultsPage.pageBtns.get(searchResultsPage.pageBtns.size() - 2).click();
        return this;
    }

    @Step("Get the title of the first card")
    public String getFirstCardTitle() {
        return searchResultsPage.cardTitles.first().getText();
    }

    @Step("Go to the next page")
    public SearchResultsSteps goToTheNextPage() {
        searchResultsPage.nextPageBtn.click();
        return this;
    }

    @Step("Go to the previous page")
    public SearchResultsSteps goToThePreviousPage() {
        searchResultsPage.previousBtn.click();
        return this;
    }

    @Step("Assert that the first card title matches '{firstCardTitle}' after navigation")
    public void previousBtnAssert(String firstCardTitle) {
        searchResultsPage.cardTitles.first().shouldHave(text(firstCardTitle));
    }

    @Step("Click on the first card")
    public SearchResultsSteps clickOnFirstCard() {
        searchResultsPage.cardTitles.first().click();
        return this;
    }

    @Step("Click on the location button")
    public SearchResultsSteps clickOnLocation() {
        searchResultsPage.locationBtn.click();
        return this;
    }

    @Step("Validate the scroll operation by verifying map visibility")
    public void validateScroll() {
        searchResultsPage.mapElement.shouldBe(visible);
    }

    @Step("Filter search results by number of guests")
    public void filterGuests() {
        searchResultsPage.numberOfGuestsFilter.click();
    }

    @Step("Extract and save guest amount from card titles")
    public List<String> saveGuestAmountFromTitle() {
        String regex = "(?<!\\d)(\\d{1,2}[+-]?\\d{0,2})(?=\\s+(ადამიანზე|სტუმარზე))";
        Pattern pattern = Pattern.compile(regex);

        List<String> extractedAmounts = new ArrayList<>();

        searchResultsPage.cardTitles.forEach(el -> {
            Matcher matcher = pattern.matcher(el.getText());
            if (matcher.find()) {
                String cleanedMatch = matcher.group(1).replace("+", " ").replace("-", " ");
                extractedAmounts.add(cleanedMatch);
            }
        });

        return extractedAmounts;
    }

    @Step("Save the upper bound of the guest filter")
    public String saveGuestUpperBounds() {
        return searchResultsPage.numberOfGuestsFilter.getText().replace("-", " ").split(" ")[1];
    }

    @Step("Save the lower bound of the guest filter")
    public String saveGuestLowerBounds() {
        return searchResultsPage.numberOfGuestsFilter.getText().replace("-", " ").split(" ")[0];
    }

    @Step("Validate the guest filter with bounds '{lowerBound}' to '{upperBounnd}'")
    public void validateFilter(List<String> filteredOffersByGuest, String lowerBound, String upperBounnd) {
        List<String> filteredAndValidatedOffers = extractOffersInRange(filteredOffersByGuest, Integer.parseInt(lowerBound), Integer.parseInt(upperBounnd));
        softAssert.assertEquals(filteredAndValidatedOffers.size(), filteredOffersByGuest.size());
    }

}
