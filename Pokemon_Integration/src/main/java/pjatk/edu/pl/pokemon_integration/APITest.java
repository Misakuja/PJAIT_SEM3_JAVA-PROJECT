package pjatk.edu.pl.pokemon_integration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class APITest {

    private static final String POKEAPI_BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String pokemonName = "pikachu";

        String url = POKEAPI_BASE_URL + pokemonName;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Pokemon Data: " + response.getBody());
        }
    }
}