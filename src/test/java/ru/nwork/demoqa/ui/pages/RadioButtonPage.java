package ru.nwork.demoqa.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class RadioButtonPage {
    private static final String URL = "/radio-button";

    SelenideElement yesRadio = $("label[for='yesRadio']");
    SelenideElement impressiveRadio = $("label[for='impressiveRadio']");
    SelenideElement noRadio = $("#noRadio");
    SelenideElement status = $(".text-success");

    @Step("Открыть страницу с radio-button")
    public static RadioButtonPage openRadioButtonPage() {
        open(URL);
        return new RadioButtonPage();
    }

    @Step("Выбрать вариант Yes")
    public RadioButtonPage selectYes() {
        yesRadio.shouldBe(interactable).click();
        return this;
    }

    @Step("Выбрать вариант Impressive")
    public RadioButtonPage selectImpressive() {
        impressiveRadio.shouldBe(interactable).click();
        return this;
    }

    @Step("Текст статуса выбора")
    public String getStatusText() {
        return status.shouldBe(visible).text();
    }

    @Step("Выбор No отключен")
    public void checkNoDisabled() {
        noRadio.shouldBe(disabled);
    }
}
