package ge.tbc.testautomation.swoop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class StaticHeaderPage {
    public SelenideElement
            searchField = $("input[placeholder='მოძებნე კომპანია ან შეთავაზება']"),
            categoryBtn = $x(".//p[text()='კატეგორიები']/parent::button"),
            restCategory = $x("//a[@href='/category/24/dasveneba/']"),
            eatCategory = $x("//a[@href='/category/15/kveba/']"),
            mountain = $x(".//h4[text()='მთის კურორტები']"),
            langChange = $x("//img[@src='/icons/language-icon.svg']"),
            geoLang = $x("//p[text()='Georgian' or text()='ქართული']"),
            engLang = $x("//p[text()='English' or text()='Spanish']");
}

