package pjatk.edu.pl.pokemon_api.integrationTests;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;

import java.util.List;

import static io.restassured.RestAssured.*;

@SpringBootTest
public class PokemonControllerTest {

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8082/api/pokemon";
    }

    @Test
    public void getAllAbilitiesWorksCorrectly() {
        get()
                .then()
                .statusCode(200);
    }

    @Test
    public void deletePokemonWorksCorrectly() {
        delete("/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void deletePokemonThrowsNotFoundException() {
        delete("/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void addPokemonWorksCorrectly() {
        with()
                .body(new Pokemon(999, "test999", 10, 10, 10, List.of(), List.of(), List.of()))
                .header("Content-Type","application/json")
                .when()
                .post()
                .then()
                .statusCode(200);
    }

    @Test
    public void addPokemonThrowsInvalidInputExceptionDueToEmptyName() {
        with()
                .body(new Pokemon(102, "", 10, 10, 10, List.of(), List.of(), List.of()))
                .header("Content-Type","application/json")
                .when()
                .post()
                .then()
                .statusCode(400);
    }

    @Test
    public void updatePokemonWorksCorrectly() {
        with()
                .body(new Pokemon(9999, "updateCorrect", 555, 555, 555, List.of(), List.of(), List.of()))
                .header("Content-Type","application/json")
                .when()
                .patch("/3")
                .then()
                .statusCode(200);
    }

    @Test
    public void updatePokemonThrowsInvalidInputExceptionDueToEmptyName() {
        with()
                .body(new Pokemon(104, "", 10, 10, 10, List.of(), List.of(), List.of()))
                .header("Content-Type","application/json")
                .when()
                .patch("/11")
                .then()
                .statusCode(400);
    }

    @Test
    public void updatePokemonThrowsINotFoundExceptionDueToWrongId() {
        with()
                .body(new Pokemon(105, "test6", 10, 10, 10, List.of(), List.of(), List.of()))
                .header("Content-Type","application/json")
                .when()
                .patch("/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findPokemonByIdWorksCorrectly() {
        get("/id/11")
                .then()
                .statusCode(200);
    }

    @Test
    public void findPokemonByIdThrowsNotFoundException() {
        get("/id/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findPokemonByApiIdWorksCorrectly() {
        get("/apiId/80")
                .then()
                .statusCode(200);
    }

    @Test
    public void findPokemonByApiIdThrowsNotFoundException() {
        get("/apiId/10000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findPokemonByNameWorksCorrectly() {
        get("/name/weedle")
                .then()
                .statusCode(200);
    }

    @Test
    public void findPokemonByNameThrowsNotFoundException() {
        get("/name/NotFound")
                .then()
                .statusCode(404);
    }

    @Test
    public void findPokemonByBaseExperienceWorksCorrectly() {
        get("/baseExperience/216")
                .then()
                .statusCode(200);
    }

    @Test
    public void findPokemonByBaseExperienceThrowsNotFoundException() {
        get("/baseExperience/100000000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findPokemonByWeightWorksCorrectly() {
        get("/weight/69")
                .then()
                .statusCode(200);
    }

    @Test
    public void findPokemonByWeightThrowsNotFoundException() {
        get("/weight/100000000")
                .then()
                .statusCode(404);
    }

    @Test
    public void findPokemonByHeightWorksCorrectly() {
        get("height/69")
                .then()
                .statusCode(200);
    }

    @Test
    public void findPokemonByHeightThrowsNotFoundException() {
        get("/height/100000000")
                .then()
                .statusCode(404);
    }


}
