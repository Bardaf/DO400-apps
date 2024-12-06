package com.redhat.training;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(SolverResource.class)
public class SolverResourceTest {

    @Test
    public void simpleSum() {
        given()
        .when().get("6+9")
        .then()
            .statusCode(200)
            .body(is("15.0"));
    }

}
