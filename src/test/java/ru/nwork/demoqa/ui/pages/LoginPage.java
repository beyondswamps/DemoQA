package ru.nwork.demoqa.ui.pages;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import ru.nwork.demoqa.ui.data.User;

public class LoginPage {
    private final static String URL = "/login";

    public SelenideElement username = $("#userName").shouldBe(visible);
    public SelenideElement password = $("#password").should(visible);
    public SelenideElement submitButton = $("button#login").shouldBe(clickable);
    public SelenideElement wrongCredsMessageElem = $("#name");

    public LoginPage() {
    }

    @Step("Открыть страницу логина")
    public static LoginPage openLoginPage() {
        open(URL);
        return new LoginPage();
    }

    @Step("Успешная аутентификация зарегистрированным пользователем")
    public ProfilePage loginRegisteredUser(User user) {
        username.shouldBe(interactable).setValue(user.username());
        password.shouldBe(interactable).setValue(user.password());
        submitButton.scrollIntoCenter().shouldBe(clickable).click();
        return new ProfilePage();
    }

    @Step("Неудачная аутентификация. Неверный логин или пароль")
    public String loginUnregisteredUser(User user) {
        username.shouldBe(interactable).setValue(user.username());
        password.shouldBe(interactable).setValue(user.password());
        submitButton.scrollIntoCenter().shouldBe(clickable).click();
        return wrongCredsMessageElem.shouldBe(visible).text();
    }

    @Step("Текст кнопки логина")
    public String getLoginButtonText() {
        return submitButton.text();
    }

    @Step("Текст заголовка на странице логина")
    public String getPageBannerText() {
        return $("h1.text-center").text();
    }

    @Step("Текст приглашения логина")
    public String getWelcomeLoginText() {
        return $("form h5").text();
    }
}
