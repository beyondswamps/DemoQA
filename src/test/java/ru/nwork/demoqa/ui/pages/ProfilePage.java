package ru.nwork.demoqa.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {
    private final SelenideElement usernameLogged = $("#userName-value");
    private final SelenideElement logoutButton = $("button#submit");
    private final SelenideElement deleteAccountButton = $(".text-center #submit");

    @Step("Юзернейм залогиненного пользователя со страницы профиля")
    public String getUsernameLogged() {
        return usernameLogged.text();
    }

    @Step("Текст кнопки логаута")
    public String getLogoutButtonText() {
        return logoutButton.text();
    }

    @Step("Выйти из аккаунта")
    public LoginPage logout() {
        logoutButton.shouldBe(clickable).click();
        return new LoginPage();
    }

    @Step("Удалить аккаунт")
    public LoginPage deleteAccount() {
        deleteAccountButton.shouldBe(interactable).pressEnter();
        $("#closeSmallModal-ok").shouldBe(clickable).click();
        return new LoginPage();
    }
}
