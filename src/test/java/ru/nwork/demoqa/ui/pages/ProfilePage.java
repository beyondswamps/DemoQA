package ru.nwork.demoqa.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {
    private final SelenideElement usernameLogged = $("#userName-value");
    private final SelenideElement logoutButton = $("button#submit");
    private final SelenideElement deleteAccountButton = $(".text-center #submit");

    public String getUsernameLogged() {
        return usernameLogged.text();
    }

    public String getLogoutButtonText() {
        return logoutButton.text();
    }

    public LoginPage logout() {
        logoutButton.shouldBe(clickable).click();
        return new LoginPage();
    }

    public LoginPage deleteAccount() {
        deleteAccountButton.shouldBe(interactable).pressEnter();
        $("#closeSmallModal-ok").shouldBe(clickable).click();
        return new LoginPage();
    }
}
