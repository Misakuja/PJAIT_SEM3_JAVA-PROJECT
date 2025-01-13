package pjatk.edu.pl.pokemon_api.integrationTests;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Type;
import pjatk.edu.pl.pokemon_data.repository.TypeRepository;

import java.util.Optional;

import static io.restassured.RestAssured.*;

@SpringBootTest
public class MoveControllerTest {

    @Autowired
    private TypeRepository typeRepository;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8082/api/move";
    }

    @Test
    public void getAllAbilitiesWorksCorrectly() {
        get()
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteMoveWorksCorrectly() {
        delete("/20")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteMoveThrowsNotFoundException() {
        delete("/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void addMoveWorksCorrectly() {
        with()
                .body(new Move(99, "test99", 10, 10, 10, typeRepository.findById(20L).get()))
                .header("Content-Type","application/json")
                .when()
                .post()
                .then()
                .statusCode(200);
    }

    @Test
    public void addMoveThrowsInvalidInputExceptionDueToEmptyName() {

        with()
                .body(new Move(102, "", 10, 10, 10, typeRepository.findById(20L).get()))
                .header("Content-Type","application/json")
                .when()
                .post()
                .then()
                .statusCode(400);
    }

    @Test
    public void addMoveThrowsInvalidInputExceptionDueToEmptyApiId() {
        Move move = new Move();
        move.setName("test");

        with()
                .body(move)
                .header("Content-Type","application/json")
                .when()
                .post()
                .then()
                .statusCode(400);
    }


    @Test
    public void updateMoveWorksCorrectly() {
        with()
                .body(new Move(9997, "test997", 100, 5, 5, typeRepository.findById(20L).get()))
                .header("Content-Type","application/json")
                .when()
                .patch("/16")
                .then()
                .statusCode(200);
    }

    @Test
    public void updateMoveThrowsInvalidInputExceptionDueToEmptyName() {
        with()
                .body(new Move(104, "", 10, 10, 10, typeRepository.findById(20L).get()))
                .header("Content-Type","application/json")
                .when()
                .patch("/11")
                .then()
                .statusCode(400);
    }

    @Test
    public void updateMoveThrowsInvalidInputExceptionDueToEmptyApiId() {
        Move move = new Move();
        move.setName("test5");

        with()
                .body(move)
                .header("Content-Type","application/json")
                .when()
                .patch("/10")
                .then()
                .statusCode(400);
    }

    @Test
    public void updateMoveThrowsINotFoundExceptionDueToWrongId() {
        with()
                .body(new Move(105, "test6", 10, 10, 10, typeRepository.findById(20L).get()))
                .header("Content-Type","application/json")
                .when()
                .patch("/10000")
                .then()
                .statusCode(400);
    }

    @Test
    public void findMoveByIdWorksCorrectly() {
        get("/id/11")
                .then()
                .statusCode(200);
    }

    @Test
    public void findMoveByIdThrowsNotFoundException() {
        get("/id/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findMoveByApiIdWorksCorrectly() {
        get("/apiId/80")
                .then()
                .statusCode(200);
    }

    @Test
    public void findMoveByApiIdThrowsNotFoundException() {
        get("/apiId/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findMoveByNameWorksCorrectly() {
        get("/name/cut")
                .then()
                .statusCode(200);
    }

    @Test
    public void findMoveByNameThrowsNotFoundException() {
        get("/name/NotFound")
                .then()
                .statusCode(404);
    }

    @Test
    public void findMoveByAccuracyWorksCorrectly() {
        get("/accuracy/100")
                .then()
                .statusCode(200);
    }

    @Test
    public void findMoveByAccuracyThrowsNotFoundException() {
        get("/accuracy/100000000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findMoveByPowerWorksCorrectly() {
        get("/power/40")
                .then()
                .statusCode(200);
    }

    @Test
    public void findMoveByPowerThrowsNotFoundException() {
        get("/power/100000000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findMoveByPpWorksCorrectly() {
        get("pp/35")
                .then()
                .statusCode(200);
    }

    @Test
    public void findMoveByPpThrowsNotFoundException() {
        get("/pp/100000000")
                .then()
                .statusCode(404);
    }


}
