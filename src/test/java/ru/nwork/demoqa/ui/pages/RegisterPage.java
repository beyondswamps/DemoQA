package ru.nwork.demoqa.ui.pages;

import com.codeborne.selenide.SelenideElement;
import ru.nwork.demoqa.ui.data.User;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;


public class RegisterPage {
    private static final String URL = "/register";

    private final SelenideElement firstnameInput = $("#firstname");
    private final SelenideElement lastnameInput = $("#lastname");
    private final SelenideElement usernameInput = $("#userName");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement registerButton = $("#register");

    private final SelenideElement captcha = $("#recaptcha-anchor");

    public static RegisterPage openRegisterPage() {
        open(URL);
        return new RegisterPage();
    }

    public RegisterPage() {}

    public LoginPage register(User user) {
        firstnameInput.setValue(user.firstName());
        lastnameInput.setValue(user.lastName());
        usernameInput.setValue(user.username());
        passwordInput.setValue(user.password());
        registerButton.click();
        return new LoginPage();
    }

    public RegisterPage fillFieldsWithUser(User user) {
        firstnameInput.shouldBe(editable).setValue(user.firstName());
        lastnameInput.shouldBe(editable).setValue(user.lastName());
        usernameInput.shouldBe(editable).setValue(user.username());
        passwordInput.shouldBe(editable).setValue(user.password());
        return this;
    }

    public RegisterPage clickRegister() {
        registerButton.shouldBe(interactable).pressEnter();
        return new RegisterPage();
    }

    public RegisterPage clickRecaptcha() {
        SelenideElement frame = $x("//iframe[@title='reCAPTCHA']");
        switchTo().frame(frame);
        $(".recaptcha-checkbox-border").shouldBe(clickable).click();
        $(".recaptcha-checkbox-checked").shouldBe(visible);
        switchTo().defaultContent();
        return this;
    }

}
