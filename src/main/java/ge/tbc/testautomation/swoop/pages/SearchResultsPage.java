package ge.tbc.testautomation.swoop.pages;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class SearchResultsPage {
    public SelenideElement
            categoryTitle = $x("//button[contains(@id, 'headlessui-menu-button')]/ancestor::div//h3"),
            card = $x("// h4[contains(@class, 'text-primary_black-100-value text-2md leading-5 font-tbcx-bold')]/ancestor::a"),
            searchErrorMsg = $x("//h2[text()='შეთავაზება არ მოიძებნა']"),
            nextPageBtn = $x("// img[@alt='right arrow']/ parent::div"),
            previousBtn = $x("// img[@alt='left arrow']/ parent::div"),
            locationBtn = $x("//p[text()='მდებარეობა']"),
            mapElement = $x("(.//h4[text()='მდებარეობა']/parent::div/parent::div/div)[last()]"),
            numberOfGuestsFilter = $x("//input[@id='radio-სტუმრების რაოდენობა-1']/parent::label/span");


    public ElementsCollection
            cardTitles = $$x("// h4[contains(@class, 'text-primary_black-100-value text-2md leading-5 font-tbcx-bold')]/ancestor::a//h4[@weight='medium']"),
            pageBtns = $$x(".//img[@alt='right arrow']/parent::div/parent::div/div[not(.//img)]"),
            labelsForLangCheck = $$x("//a//p[not(contains(., '@'))] | //h4 | //span");
}