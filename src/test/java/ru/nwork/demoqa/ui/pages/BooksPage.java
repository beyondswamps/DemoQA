package ru.nwork.demoqa.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BooksPage {
    public static final String URL="/books";

    private final SelenideElement loginButton = $("button#login").shouldBe(clickable);

    public BooksPage() {};

    public static BooksPage openBooksPage() {
        open(URL);
        return new BooksPage();
    }

    public LoginPage openLoginPage() {
        loginButton.click();
        return new LoginPage();
    }
}
