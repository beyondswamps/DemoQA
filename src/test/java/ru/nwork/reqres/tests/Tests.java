package ru.nwork.reqres.tests;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nwork.reqres.models.UserCreds;
import ru.nwork.reqres.responses.ErrorResponse;
import ru.nwork.reqres.specifications.Specifications;
import ru.nwork.reqres.util.UsersHelper;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.nullValue;

public class Tests {
    public static final String URL = "https://reqres.in";

    @Test
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
                        .log().all()
                        .spec(responseSpecBad400)
                        .extract().as(ErrorResponse.class);

        Assertions.assertEquals("Missing password", errorResponse.error);
    }

    @Test
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
