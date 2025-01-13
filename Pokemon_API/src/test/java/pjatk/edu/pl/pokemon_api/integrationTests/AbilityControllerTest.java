package pjatk.edu.pl.pokemon_api.integrationTests;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pjatk.edu.pl.pokemon_data.entity.Ability;

import static io.restassured.RestAssured.*;

@SpringBootTest
public class AbilityControllerTest {

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8082/api/ability";
    }

    @Test
    public void getAllAbilitiesWorksCorrectly() {
        get()
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteAbilityWorksCorrectly() {
        delete("/11")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteAbilityThrowsNotFoundException() {
        delete("/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void addAbilityWorksCorrectly() {
        with()
                .body(new Ability(9997, "test9997"))
                .header("Content-Type","application/json")
                .when()
                .post()
                .then()
                .statusCode(200);
    }

    @Test
    public void addAbilityThrowsInvalidInputExceptionDueToEmptyName() {
        with()
                .body(new Ability(10, ""))
                .header("Content-Type","application/json")
                .when()
                .post()
                .then()
                .statusCode(400);
    }

    @Test
    public void updateAbilityWorksCorrectly() {
        with()
                .body(new Ability(995, "test995"))
                .header("Content-Type","application/json")
                .when()
                .patch("/5")
                .then()
                .statusCode(200);
    }

    @Test
    public void updateAbilityThrowsInvalidInputExceptionDueToEmptyName() {
        with()
                .body(new Ability(10, ""))
                .header("Content-Type","application/json")
                .when()
                .patch("/12")
                .then()
                .statusCode(400);
    }

    @Test
    public void updateAbilityThrowsINotFoundExceptionDueToWrongId() {
        with()
                .body(new Ability(10, "test6"))
                .header("Content-Type","application/json")
                .when()
                .patch("/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findAbilityByIdWorksCorrectly() {
        get("/id/13")
                .then()
                .statusCode(200);
    }

    @Test
    public void findAbilityByIdThrowsNotFoundException() {
        get("/id/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findAbilityByApiIdWorksCorrectly() {
        get("/apiId/80")
                .then()
                .statusCode(200);
    }

    @Test
    public void findAbilityByApiIdThrowsNotFoundException() {
        get("/apiId/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findAbilityByNameWorksCorrectly() {
        get("/name/hydration")
                .then()
                .statusCode(200);
    }

    @Test
    public void findAbilityByNameThrowsNotFoundException() {
        get("/name/NotFound")
                .then()
                .statusCode(404);
    }


}
