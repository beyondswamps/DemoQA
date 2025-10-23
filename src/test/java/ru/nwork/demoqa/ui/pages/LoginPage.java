package ru.nwork.demoqa.ui.pages;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.nwork.demoqa.ui.data.User;

public class LoginPage {
    private final static String URL = "/login";

    public SelenideElement username = $("#userName").shouldBe(visible);
    public SelenideElement password = $("#password").should(visible);
    public SelenideElement submitButton = $("button#login").shouldBe(clickable);
    public SelenideElement wrongCredsMessageElem = $("#name");

    public LoginPage() {}

    public static LoginPage openLoginPage() {
        open(URL);
        return new LoginPage();
    }

    public ProfilePage loginUsernamePassword(String userName, String password) {
        this.username.sendKeys(userName);
        this.password.sendKeys(password);
        submitButton.click();
        return new ProfilePage();
    }

    public ProfilePage loginRegisteredUser(User user) {
        username.shouldBe(interactable).sendKeys(user.username());
        password.shouldBe(interactable).sendKeys(user.password());
        submitButton.shouldBe(clickable).click();
        return new ProfilePage();
    }

    public String loginUnregisteredUser(User user) {
        username.shouldBe(interactable).sendKeys(user.username());
        password.shouldBe(interactable).sendKeys(user.password());
        submitButton.shouldBe(clickable).click();
        return wrongCredsMessageElem.shouldBe(visible).text();
    }

}
