package pjatk.edu.pl.pokemon_api.integrationTests;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pjatk.edu.pl.pokemon_data.entity.Type;

import static io.restassured.RestAssured.*;

@SpringBootTest
public class TypeControllerTest {

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8082/api/type";
    }

    @Test
    public void getAllAbilitiesWorksCorrectly() {
        get()
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteTypeWorksCorrectly() {
        delete("/3")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteTypeThrowsNotFoundException() {
        delete("/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void addTypeWorksCorrectly() {
        with()
                .body(new Type(10, "test9999"))
                .header("Content-Type","application/json")
                .when()
                .post()
                .then()
                .statusCode(200);
    }

    @Test
    public void addTypeThrowsInvalidInputExceptionDueToEmptyName() {
        with()
                .body(new Type(10, ""))
                .header("Content-Type","application/json")
                .when()
                .post()
                .then()
                .statusCode(400);
    }

    @Test
    public void updateTypeWorksCorrectly() {
        with()
                .body(new Type(10, "test9998"))
                .header("Content-Type","application/json")
                .when()
                .patch("/6")
                .then()
                .statusCode(200);
    }

    @Test
    public void updateTypeThrowsInvalidInputExceptionDueToEmptyName() {
        with()
                .body(new Type(10, ""))
                .header("Content-Type","application/json")
                .when()
                .patch("/2")
                .then()
                .statusCode(400);
    }

    @Test
    public void updateTypeThrowsINotFoundExceptionDueToWrongId() {
        with()
                .body(new Type(10, "test6"))
                .header("Content-Type","application/json")
                .when()
                .patch("/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findTypeByIdWorksCorrectly() {
        get("/id/11")
                .then()
                .statusCode(200);
    }

    @Test
    public void findTypeByIdThrowsNotFoundException() {
        get("/id/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findTypeByApiIdWorksCorrectly() {
        get("/apiId/19")
                .then()
                .statusCode(200);
    }

    @Test
    public void findTypeByApiIdThrowsNotFoundException() {
        get("/apiId/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findTypeByNameWorksCorrectly() {
        get("/name/dragon")
                .then()
                .statusCode(200);
    }

    @Test
    public void findTypeByNameThrowsNotFoundException() {
        get("/name/NotFound")
                .then()
                .statusCode(404);
    }


}
