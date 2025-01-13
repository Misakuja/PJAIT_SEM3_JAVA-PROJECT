package pjatk.edu.pl.pokemon_api.integrationTests;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pjatk.edu.pl.pokemon_data.entity.Item;

import static io.restassured.RestAssured.*;

@SpringBootTest
public class ItemControllerTest {

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8082/api/item";
    }

    @Test
    public void getAllAbilitiesWorksCorrectly() {
        get()
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteItemWorksCorrectly() {
        delete("/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteItemThrowsNotFoundException() {
        delete("/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void addItemWorksCorrectly() {
        with()
                .body(new Item(99, "addWorks"))
                .header("Content-Type","application/json")
                .when()
                .post()
                .then()
                .statusCode(200);
    }

    @Test
    public void addItemThrowsInvalidInputExceptionDueToEmptyName() {
        with()
                .body(new Item(10, ""))
                .header("Content-Type","application/json")
                .when()
                .post()
                .then()
                .statusCode(400);
    }


    @Test
    public void updateItemWorksCorrectly() {
        with()
                .body(new Item(10, "test3"))
                .header("Content-Type","application/json")
                .when()
                .patch("/5")
                .then()
                .statusCode(200);
    }

    @Test
    public void updateItemThrowsInvalidInputExceptionDueToEmptyName() {
        with()
                .body(new Item(10, ""))
                .header("Content-Type","application/json")
                .when()
                .patch("/11")
                .then()
                .statusCode(400);
    }

    @Test
    public void updateItemThrowsINotFoundExceptionDueToWrongId() {
        with()
                .body(new Item(10, "test6"))
                .header("Content-Type","application/json")
                .when()
                .patch("/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findItemByIdWorksCorrectly() {
        get("/id/11")
                .then()
                .statusCode(200);
    }

    @Test
    public void findItemByIdThrowsNotFoundException() {
        get("/id/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findItemByApiIdWorksCorrectly() {
        get("/apiId/80")
                .then()
                .statusCode(200);
    }

    @Test
    public void findItemByApiIdThrowsNotFoundException() {
        get("/apiId/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findItemByNameWorksCorrectly() {
        get("/name/dusk-ball")
                .then()
                .statusCode(200);
    }

    @Test
    public void findItemByNameThrowsNotFoundException() {
        get("/name/NotFound")
                .then()
                .statusCode(404);
    }


}
