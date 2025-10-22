package ru.nwork.demoqa.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    private final static String URL = "/login";

    @FindBy(css = "#userName")
    public SelenideElement userName;

    @FindBy(css = "#password")
    public SelenideElement password;

    @FindBy(css="button#login")
    public SelenideElement submitButton;

}
