package ru.nwork.demoqa.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BooksPage {
    public static final String URL="/books";

    private final SelenideElement loginButton = $("button#login").shouldBe(clickable);

    public BooksPage() {};

    @Step("Открыть главную страницу магазина с книгами")
    public static BooksPage openBooksPage() {
        open(URL);
        return new BooksPage();
    }

    @Step("Перейти на страницу логина")
    public LoginPage openLoginPage() {
        loginButton.click();
        return new LoginPage();
    }
}
