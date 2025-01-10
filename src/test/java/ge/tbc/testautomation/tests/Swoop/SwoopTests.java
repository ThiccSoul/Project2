package ge.tbc.testautomation.tests.Swoop;

import com.codeborne.selenide.Selenide;
import ge.tbc.testautomation.configuration.TestConfig;
import ge.tbc.testautomation.dataproviders.SearchInputsDataProvider;
import ge.tbc.testautomation.swoop.steps.HomeSteps;
import ge.tbc.testautomation.swoop.steps.SearchResultsSteps;
import ge.tbc.testautomation.swoop.steps.StaticHeaderSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;

@Test(groups = "SwoopRegression")
@Epic("Swoop Testing Suite")
public class SwoopTests extends TestConfig {
    SearchResultsSteps searchResultsSteps = new SearchResultsSteps();
    StaticHeaderSteps staticHeaderSteps = new StaticHeaderSteps();
    HomeSteps homeSteps = new HomeSteps();
    @BeforeMethod
    public void startUp() {
        open("https://swoop.ge/");
    }

    @Test(dataProvider = "searchInputData", dataProviderClass = SearchInputsDataProvider.class)
    @Feature("Search Functionality")
    @Story("As a user, I want to search for items to find relevant results")
    @Severity(SeverityLevel.CRITICAL)
    public void searchTest(String searchTerm) {
        staticHeaderSteps
                .searchFill(searchTerm)
                .clickOnSearch()
                .searchRun();
        searchResultsSteps
                .searchAssert(searchTerm);
        Boolean exist = searchResultsSteps.categoryAssert();
        searchResultsSteps.notFoundAssert(exist);
    }

    @Test
    @Feature("Pagination Functionality")
    @Story("As a user, I want to navigate pages to explore all available items")
    @Severity(SeverityLevel.NORMAL)
    public void paginationTest() {
        staticHeaderSteps
                .clickOnCategory()
                .hoverOnRest()
                .clickOnMountain();
        searchResultsSteps
                .waitForPageBtn()
                .iteratePagesAndSaveCardTexts()
                .assertThatCardTitlesAreUnique();
        String firstTitleNext = searchResultsSteps.getFirstCardTitle();
        searchResultsSteps
                .clickingOnFifthPageBtn()
                .goToTheNextPage()
                .previousBtnAssert(firstTitleNext);
        searchResultsSteps.clickingOnFifthPageBtn();
        String firstTitlePrevious = searchResultsSteps.getFirstCardTitle();
        System.out.println(firstTitlePrevious);
        searchResultsSteps
                .goToTheNextPage()
                .goToThePreviousPage()
                .previousBtnAssert(firstTitlePrevious);

        Selenide.sleep(5000);
    }

    @Test
    @Feature("Offer Details Functionality")
    @Story("As a user, I want to view offer locations on a map")
    @Severity(SeverityLevel.MINOR)
    public void offerLocationTest() {
        staticHeaderSteps
                .clickOnCategory()
                .hoverOnRest()
                .clickOnMountain();
        searchResultsSteps
                .clickOnFirstCard()
                .clickOnLocation()
                .validateScroll();
    }

    @Test
    @Feature("Filter Functionality")
    @Story("As a user, I want to filter offers by the number of guests")
    @Severity(SeverityLevel.NORMAL)
    public void numberOfGuestsTest() {
        staticHeaderSteps
                .clickOnCategory()
                .clickOnEat();
        searchResultsSteps
                .filterGuests();
        List<String> filteredOffersByGuest = searchResultsSteps.saveGuestAmountFromTitle();
        searchResultsSteps
                .validateFilter(filteredOffersByGuest, searchResultsSteps.saveGuestLowerBounds(), searchResultsSteps.saveGuestUpperBounds());
    }

    @Test
    @Feature("Language Change Functionality")
    @Story("As a user, I want to switch between Georgian and English languages")
    @Severity(SeverityLevel.NORMAL)
    public void changeLanguageTest() {
        staticHeaderSteps
                .clickOnChangeLang()
                .setLangToEng()
                .waitUntillLangIsEng();
        homeSteps
                .validateTextIsInEnglish();
        staticHeaderSteps
                .clickOnChangeLang()
                .setLangToGeo()
                .waitUntillLangIsGeo();
        homeSteps
                .validateTextIsInGeorgian();
    }
}
