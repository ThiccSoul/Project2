package ge.tbc.testautomation.saucedemo.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage {

    public SelenideElement
            burgerMenu = $x("//button[text()='Open Menu']"),
            logoutElement = $x("//a[@id='logout_sidebar_link']");
    public ElementsCollection
            imgElements = $(".inventory_list").$$x(".//img");


    // this is for Conflict
}
