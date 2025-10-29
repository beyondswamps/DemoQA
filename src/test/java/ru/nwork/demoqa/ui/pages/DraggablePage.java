package ru.nwork.demoqa.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.Point;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.open;

@Getter
public class DraggablePage {
    private static final String URL = "/elements";

    private final SelenideElement simpleTab = $("#draggableExample-tab-simple");
    private final SelenideElement axisRestrictedTab = $("#draggableExample-tab-axisRestriction");
    private final SelenideElement containerRestrictedTab = $("#draggableExample-tab-containerRestriction");
    private final SelenideElement cursorStyleTab = $("#draggableExample-tabpane-cursorStyle");

    private final SelenideElement dragBox = $("#dragBox");
    private final SelenideElement restrictedXBox = $("#restrictedX");
    private final SelenideElement restrictedYBox = $("#restrictedY");
    private final SelenideElement dragContRestrictedBox = $("#containmentWrapper div");
    private final SelenideElement dragContRestrictedContainerWrapper = $("#containmentWrapper");


    @Step("Открыть страницу Draggable")
    public static DraggablePage openDraggablePage() {
        open(URL);
        $x("//div[text()='Interactions']").scrollIntoCenter().shouldBe(clickable).click();
        $x("//span[text()='Dragabble']").scrollIntoCenter().shouldBe(clickable).click();
        return new DraggablePage();
    }

    @Step("Выбрать вкладку Simple")
    public DraggablePage selectSimpleTab() {
        simpleTab.scrollTo().shouldBe(clickable).click();
        return this;
    }

    @Step("Выбрать вкладку Axis Restricted")
    public DraggablePage selectAxisRestrictedTab() {
        axisRestrictedTab.scrollTo().shouldBe(clickable).click();
        return this;
    }

    @Step("Выбрать вкладку Container Restricted")
    public DraggablePage selectContainerRestrictedTab() {
        containerRestrictedTab.scrollTo().shouldBe(clickable).click();
        return this;
    }

    @Step("Drug'n'Drop элемента {element} на {xOffset} по X и на {yOffset} по Y")
    public Point dragAndDropElement(SelenideElement element, Integer xOffset, Integer yOffset) {
        actions().dragAndDropBy(element, xOffset, yOffset).perform();
        return element.getLocation();
    }

    @Step("Получить расположение элемента {element}")
    public Point getElementLocation(SelenideElement element) {
        return element.getLocation();
    }

    @Step("Получить максимальную позицию элемента {element} внутри контейнера {container}")
    public Point getMaxPointForElemContainer(SelenideElement element, SelenideElement container) {
        Point containerPos = container.shouldBe(visible).getLocation();
        Point containerMaxDimension = new Point (containerPos.x + container.getSize().width, containerPos.y + container.getSize().height);
        String cssStringMargin = container.getCssValue("margin");
        int containerMargin = Integer.parseInt(cssStringMargin.substring(0, cssStringMargin.length() - 2));
        String cssStringPadding = container.getCssValue("padding");
        int containerPadding = Integer.parseInt(cssStringPadding.substring(0, cssStringPadding.length() - 2));
        int elementSizeX = element.getSize().width;
        int elementSizeY = element.getSize().height;
        return new Point(
                containerMaxDimension.x - containerMargin - containerPadding - elementSizeX,
                containerMaxDimension.y - containerMargin - containerPadding - elementSizeY
        );
    }
}
