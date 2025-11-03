package ru.nwork.reqres.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.nwork.reqres.models.UserCreds;
import ru.nwork.reqres.responses.ErrorResponse;
import ru.nwork.reqres.specifications.Specifications;
import ru.nwork.reqres.util.UsersHelper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.nullValue;

@Feature("reqres.in API")
@DisplayName("reqres.in API. Тесты регистрации, логина")
public class AuthTests {
    public static final String URL = "https://reqres.in";

    @Test
    @DisplayName("Регистрация пользователя")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void register() {
        RequestSpecification requestSpec = Specifications.requestSpecification(URL);
        ResponseSpecification responseSpecOk200 = Specifications.responseSpecificationOk200();

        UserCreds userCreds = UsersHelper.userCredsForRegister;

        given()
                .spec(requestSpec)
                .body(userCreds)
        .when()
                .post("/api/register")
        .then()
                .spec(responseSpecOk200)
                .log().all()
                .body("id", not(nullValue()))
                .body("token", not(nullValue()));

    }

    @Test
    @DisplayName("Регистрация с пустым паролем")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void registerEmptyPassword() {
        RequestSpecification requestSpec = Specifications.requestSpecification(URL);
        ResponseSpecification responseSpecBad400 = Specifications.responseSpecificationBad400();

        UserCreds userCreds = new UserCreds("charles.morris@reqres.in", "");

        ErrorResponse errorResponse =
                given()
                        .spec(requestSpec)
                        .body(userCreds)
                .when()
                        .post("/api/register")
                .then()
                        .spec(responseSpecBad400)
                        .log().all()
                        .extract().as(ErrorResponse.class);

        Assertions.assertEquals("Missing password", errorResponse.error);
    }

    @Test
    @DisplayName("Логин зарегистрированного пользователя")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void loginWithRegistered() {
        RequestSpecification requestSpec = Specifications.requestSpecification(URL);
        ResponseSpecification responseSpecOk200 = Specifications.responseSpecificationOk200();

        UserCreds userCreds = new UserCreds("charles.morris@reqres.in", "pistol");

                given()
                        .spec(requestSpec)
                        .body(userCreds)
                .when()
                        .post("/api/login")
                .then()
                        .log().all()
                        .spec(responseSpecOk200)
                        .body("token", not(nullValue()));

    }

    @Test
    @DisplayName("Логин незарегистрированного пользователя")
    @Owner("Oleg Zabolotnykh<beyondswamps@gmail.com>")
    public void loginWithNotRegistered() {
        RequestSpecification requestSpec = Specifications.requestSpecification(URL);
        ResponseSpecification responseSpecBad400 = Specifications.responseSpecificationBad400();

        UserCreds userCreds = new UserCreds("uknown.user@reqres.in", "pistol");

        ErrorResponse errorResponse =
                given()
                        .spec(requestSpec)
                        .body(userCreds)
                .when()
                        .post("/api/login")
                .then()
                        .log().all()
                        .spec(responseSpecBad400)
                        .extract().as(ErrorResponse.class);

        Assertions.assertEquals("user not found", errorResponse.error);
    }
}
