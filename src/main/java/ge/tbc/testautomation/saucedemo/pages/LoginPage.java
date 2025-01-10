package ge.tbc.testautomation.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    public SelenideElement
            userNameField = $x("//input[@id='user-name']"),
            passwordField = $x("//input[@id='password']"),
            loginButton = $x("//input[@id='login-button']\n"),
            loginErrElement = $x("//h3[contains(text(), 'Epic sadface')]"),
            loginErrElXButton = $x("//h3[contains(text(), 'Epic sadface')]/Button");

    // this is for Conflict15

}
