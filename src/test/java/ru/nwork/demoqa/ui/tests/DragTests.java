package ru.nwork.demoqa.ui.tests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import ru.nwork.demoqa.ui.pages.DraggablePage;

@Feature("Elements")
@Story("Проверка Drug'n'Drop")
@DisplayName("Drug'n'Drop объектов")
public class DragTests extends BaseTest {
    @Test
    @DisplayName("Drag'n'Drop свободно перемещаемого объекта")
    public void simpleTabDragBox() {
        DraggablePage draggablePage = DraggablePage.openDraggablePage().selectSimpleTab();
        SelenideElement dragBox = draggablePage.getDragBox();

        Point point0 = draggablePage.getElementLocation(dragBox);
        Point point1 = draggablePage.dragAndDropElement(dragBox, 10, 14);
        Point point2 = draggablePage.dragAndDropElement(dragBox, 111, 123);
        Point point3 = draggablePage.dragAndDropElement(dragBox, -90, -32);
        Point point4 = draggablePage.dragAndDropElement(dragBox, 100, 200);

        Assertions.assertEquals(10, point1.x - point0.x);
        Assertions.assertEquals(14, point1.y - point0.y);
        Assertions.assertEquals(111, point2.x - point1.x);
        Assertions.assertEquals(123, point2.y - point1.y);
        Assertions.assertEquals(-90, point3.x - point2.x);
        Assertions.assertEquals(-32, point3.y - point2.y);
        Assertions.assertEquals(100, point4.x - point3.x);
        Assertions.assertEquals(200, point4.y - point3.y);

    }

    @Test
    @DisplayName("Перемещение объектов, залоченных на осях X и Y")
    public void axisRestrictedDragBox() {
        DraggablePage draggablePage = DraggablePage.openDraggablePage().selectAxisRestrictedTab();
        SelenideElement restrictedXBox = draggablePage.getRestrictedXBox();
        SelenideElement restrictedYBox = draggablePage.getRestrictedYBox();

        Point point0XBox = draggablePage.getElementLocation(restrictedXBox);
        Point point1XBox = draggablePage.dragAndDropElement(restrictedXBox, 10, 14);
        Point point2XBox = draggablePage.dragAndDropElement(restrictedXBox, -85, -107);

        Point point0YBox = draggablePage.getElementLocation(restrictedYBox);
        Point point1YBox = draggablePage.dragAndDropElement(restrictedYBox, 25, 61);
        Point point2YBox = draggablePage.dragAndDropElement(restrictedYBox, -34, -56);

        Assertions.assertEquals(10, point1XBox.x - point0XBox.x);
        Assertions.assertEquals(point1XBox.y, point0XBox.y);
        Assertions.assertEquals(-85, point2XBox.x - point1XBox.x);
        Assertions.assertEquals(point2XBox.y, point1XBox.y);

        Assertions.assertEquals(point1YBox.x, point0YBox.x);
        Assertions.assertEquals(61, point1YBox.y - point0YBox.y);
        Assertions.assertEquals(point2YBox.x, point1YBox.x);
        Assertions.assertEquals(-56, point2YBox.y - point1YBox.y);
    }

    @Test
    @DisplayName("Ограниченное перемещение объекта внутри контейнера")
    public void containerRestrictedDragBox() {
        DraggablePage draggablePage = DraggablePage.openDraggablePage().selectContainerRestrictedTab();
        SelenideElement dragBox = draggablePage.getDragContRestrictedBox();
        SelenideElement wrapper = draggablePage.getDragContRestrictedContainerWrapper();

        Point maxPointOfDragBox = draggablePage.getMaxPointForElemContainer(dragBox, wrapper);

        Assertions.assertDoesNotThrow(() ->
                draggablePage.dragAndDropElement(dragBox, maxPointOfDragBox.x, maxPointOfDragBox.y));
        Assertions.assertThrows(MoveTargetOutOfBoundsException.class, () ->
                draggablePage.dragAndDropElement(dragBox, maxPointOfDragBox.x + 3, maxPointOfDragBox.y + 3));

    }
}
