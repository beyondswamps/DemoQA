package ru.nwork.demoqa.ui.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;


public class BaseTest {
    @BeforeAll
    static void setUp() {

    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
