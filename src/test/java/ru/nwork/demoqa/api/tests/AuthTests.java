package ru.nwork.demoqa.api.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
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

@Feature("Bookstore-RestApi")
@Story("Тестирование api")
@DisplayName("Тестирование api")
public class AuthTests {
    private static final String URL = "https://demoqa.com";

    @Test
    @DisplayName("Регистрация нового пользователя")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void registerTest() {
        RequestSpecification requestSpec = Specifications.requestSpecification(URL);
        ResponseSpecification responseSpec = Specifications.responseSpecificationCreated201();

        UserCreds newUser = UsersHelper.createUserReg();

        UserRegistered userRegistered =
                given()
                        .spec(requestSpec)
                        .body(newUser)
                .when()
                        .post("/Account/v1/user")
                .then()
                        .log().all()
                        .spec(responseSpec)
                        .extract().as(UserRegistered.class);

        Assertions.assertEquals(newUser.userName, userRegistered.username);
    }

    @Test
    @DisplayName("Authorize незарегистрированного пользователя")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void notAuthorized() {
        RequestSpecification requestSpec = Specifications.requestSpecification(URL);
        ResponseSpecification responseSpec = Specifications.responseSpecificationNotFound404();
        UserCreds userCreds = UsersHelper.createUserReg();

        ErrorResponse errorResponse =
                given()
                        .spec(requestSpec)
                        .body(userCreds)
                .when()
                        .post("/Account/v1/Authorized")
                .then()
                        .log().all()
                        .spec(responseSpec)
                        .extract().as(ErrorResponse.class);

        Assertions.assertEquals("User not found!", errorResponse.getMessage());
        Assertions.assertEquals("1207", errorResponse.getCode());
    }

    @Test
    @DisplayName("Authorize зарегистрированного пользователя без токена")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void notAuthorizedWithoutToken() {
        RequestSpecification requestSpec = Specifications.requestSpecification(URL);
        ResponseSpecification responseSpec = Specifications.responseSpecificationCreated201();
        UserCreds userCreds = UsersHelper.createUserReg();

        UserRegistered userRegistered =
                given()
                        .spec(requestSpec)
                        .body(userCreds)
                .when()
                        .post("/Account/v1/User")
                .then()
                        .log().all()
                        .spec(responseSpec)
                        .extract().as(UserRegistered.class);

        responseSpec = Specifications.responseSpecificationOk200();

        boolean authorizedWithoutToken =
                given()
                        .spec(requestSpec)
                        .body(userCreds)
                .when()
                        .post("/Account/v1/Authorized")
                .then()
                        .log().all()
                        .spec(responseSpec)
                        .extract().as(Boolean.class);

        Assertions.assertFalse(authorizedWithoutToken);
    }

    @Test
    @DisplayName("Authorize зарегистрированного пользователя c токеном")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void authorizedWithToken() {
        RequestSpecification requestSpec = Specifications.requestSpecification(URL);
        ResponseSpecification responseSpec = Specifications.responseSpecificationCreated201();
        UserCreds userCreds = UsersHelper.createUserReg();

        UserRegistered userRegistered =
                given()
                        .spec(requestSpec)
                        .body(userCreds)
                .when()
                        .post("/Account/v1/User")
                .then()
                        .log().all()
                        .spec(responseSpec)
                        .extract().as(UserRegistered.class);

        responseSpec = Specifications.responseSpecificationOk200();

        TokenGenerated tokenGenerated =
                given()
                        .spec(requestSpec)
                        .body(userCreds)
                .when()
                        .post("/Account/v1/GenerateToken")
                .then()
                        .log().all()
                        .spec(responseSpec)
                        .extract().as(TokenGenerated.class);

        boolean authorizedWithToken =
                given()
                        .spec(requestSpec)
                        .body(userCreds)
                .when()
                        .post("/Account/v1/Authorized")
                .then()
                        .log().all()
                        .spec(responseSpec)
                        .extract().as(Boolean.class);

        Assertions.assertTrue(authorizedWithToken);
    }
}
