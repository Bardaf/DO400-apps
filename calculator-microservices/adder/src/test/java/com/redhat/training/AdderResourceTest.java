package com.redhat.training;

import static org.mockito.Mockito.when;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import com.redhat.training.service.SolverService;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
@TestHTTPEndpoint(AdderResource.class)
public class AdderResourceTest {

    @InjectMock
    @RestClient 
    SolverService solverService;

    @Test
    public void simpleSum() {
        when(solverService.solve("5")).thenReturn(Float.valueOf("5"));
        when(solverService.solve("2")).thenReturn(Float.valueOf("2"));

        given()
        .when().get("2/5")
        .then()
            .statusCode(200)
            .body(is("7.0"));
    }

    @Test
    public void negativeSum() {
        when(solverService.solve("-5")).thenReturn(Float.valueOf("-5"));
        when(solverService.solve("2")).thenReturn(Float.valueOf("2"));

        given()
        .when().get("2/-5")
        .then()
            .statusCode(200)
            .body(is("-3.0"));
    }

    @Test
    public void impossibleSum() {
        WebApplicationException exception = new WebApplicationException("Unknown error", Response.Status.BAD_REQUEST);
        when(solverService.solve("P")).thenThrow(new ResteasyWebApplicationException(exception));
        when(solverService.solve("2")).thenReturn(Float.valueOf("2"));

        given()
        .when().get("2/P")
        .then()
            .statusCode(400);
    }

}
