package pjatk.edu.pl.pokemon_api.exception;

public class InvalidInput extends RuntimeException {
    public InvalidInput() {
        super("Invalid field input.");
    }
}
