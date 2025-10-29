package ru.nwork.demoqa.ui.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.nwork.demoqa.ui.pages.RadioButtonPage;

@Feature("Elements")
@Story("Проверка работы radio-button")
@DisplayName("Проверка работы radio-button")
public class RadioTests extends BaseTest{

    @Test
    @DisplayName("Выбор вариантов Yes, Impressive. Вариант No не выбираемый.")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void checkRadioButton() {
        RadioButtonPage radioButtonPage = RadioButtonPage.openRadioButtonPage();

        String yesSelection = radioButtonPage
                .selectYes()
                .getStatusText();

        String imprSelection = radioButtonPage
                .selectImpressive()
                .getStatusText();

        radioButtonPage
                .checkNoDisabled();

        Assertions.assertEquals("Yes", yesSelection);
        Assertions.assertEquals("Impressive", imprSelection);
    }
}
