package ru.nwork.demoqa.api.specifications;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static void setSpecifications(RequestSpecification reqSpec, ResponseSpecification respSpec) {
        RestAssured.requestSpecification = reqSpec;
        RestAssured.responseSpecification = respSpec;
    }

    public static RequestSpecification requestSpecification(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecificationOk200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpecificationOk201() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }
}
