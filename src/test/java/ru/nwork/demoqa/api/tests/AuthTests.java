package ru.nwork.demoqa.api.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nwork.demoqa.api.models.UserFull;
import ru.nwork.demoqa.api.models.UserForRegister;
import ru.nwork.demoqa.api.models.UserRegistered;
import ru.nwork.demoqa.api.specifications.Specifications;
import ru.nwork.demoqa.api.util.UsersHelper;
import static io.restassured.RestAssured.given;

public class AuthTests {
    private static final String URL = "https://demoqa.com";

    @Test
    public void registerTest() {
        Specifications.setSpecifications(Specifications.requestSpecification(URL), Specifications.responseSpecificationOk201());

        UserForRegister newUser = UsersHelper.createUserReg();

        UserRegistered userRegistered = given()
                .body(newUser)
                .when()
                .post("/Account/v1/user")
                .then()
                .log().all()
                .extract().as(UserRegistered.class);

        Assertions.assertEquals(newUser.userName, userRegistered.username);
    }
}
