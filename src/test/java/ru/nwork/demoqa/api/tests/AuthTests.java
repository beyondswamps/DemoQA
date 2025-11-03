package ru.nwork.demoqa.api.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.nwork.demoqa.api.models.UserCreds;
import ru.nwork.demoqa.api.models.UserRegistered;
import ru.nwork.demoqa.api.response.ErrorResponse;
import ru.nwork.demoqa.api.response.TokenGenerated;
import ru.nwork.demoqa.api.specifications.Specifications;
import ru.nwork.demoqa.api.util.UsersHelper;

import static io.restassured.RestAssured.given;
import static ru.nwork.demoqa.api.specifications.Specifications.requestSpecification;
import static ru.nwork.demoqa.api.specifications.Specifications.responseSpecificationCreated201;
import static ru.nwork.demoqa.api.specifications.Specifications.responseSpecificationNotFound404;
import static ru.nwork.demoqa.api.specifications.Specifications.responseSpecificationOk200;

@Feature("Bookstore-RestApi")
@Story("Тестирование api")
@DisplayName("Тестирование api")
public class AuthTests {
    private static final String URL = "https://demoqa.com";

    @Test
    @DisplayName("Регистрация нового пользователя")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void registerTest() {
        Specifications.setSpecifications(requestSpecification(URL), responseSpecificationCreated201());

        UserCreds newUser = UsersHelper.createUserReg();

        UserRegistered userRegistered = given()
                .body(newUser)
                .when()
                .post("/Account/v1/user")
                .then()
                .log().all()
                .extract().as(UserRegistered.class);

        Assertions.assertEquals(newUser.userName, userRegistered.username);
    }

    @Test
    @DisplayName("Authorize незарегистрированного пользователя")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void notAuthorized() {
        Specifications.setSpecifications(requestSpecification(URL), responseSpecificationNotFound404());
        UserCreds userCreds = UsersHelper.createUserReg();

        ErrorResponse errorResponse = given()
                .body(userCreds)
                .when()
                .post("/Account/v1/Authorized")
                .then()
                .log().all()
                .extract().as(ErrorResponse.class);

        Assertions.assertEquals("User not found!", errorResponse.getMessage());
        Assertions.assertEquals("1207", errorResponse.getCode());
    }

    @Test
    @DisplayName("Authorize зарегистрированного пользователя без токена")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void notAuthorizedWithoutToken() {
        Specifications.setSpecifications(requestSpecification(URL), responseSpecificationCreated201());
        UserCreds userCreds = UsersHelper.createUserReg();

        UserRegistered userRegistered = given()
                .body(userCreds)
                .when()
                .post("/Account/v1/User")
                .then()
                .log().all()
                .extract().as(UserRegistered.class);

        Specifications.setRespSpec(responseSpecificationOk200());

        boolean authorizedWithoutToken = given()
                .body(userCreds)
                .when()
                .post("/Account/v1/Authorized")
                .then()
                .log().all()
                .extract().as(Boolean.class);

        Assertions.assertFalse(authorizedWithoutToken);
    }

    @Test
    @DisplayName("Authorize зарегистрированного пользователя c токеном")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void authorizedWithToken() {
        Specifications.setSpecifications(requestSpecification(URL), responseSpecificationCreated201());
        UserCreds userCreds = UsersHelper.createUserReg();

        UserRegistered userRegistered = given()
                .body(userCreds)
                .when()
                .post("/Account/v1/User")
                .then()
                .log().all()
                .extract().as(UserRegistered.class);

        Specifications.setRespSpec(responseSpecificationOk200());

        TokenGenerated tokenGenerated = given()
                .body(userCreds)
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .log().all()
                .extract().as(TokenGenerated.class);

        boolean authorizedWithToken = given()
                .body(userCreds)
                .when()
                .post("/Account/v1/Authorized")
                .then()
                .log().all()
                .extract().as(Boolean.class);

        Assertions.assertTrue(authorizedWithToken);
    }
}
