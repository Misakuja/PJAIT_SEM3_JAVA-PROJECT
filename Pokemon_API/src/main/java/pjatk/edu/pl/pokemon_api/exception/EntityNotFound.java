package pjatk.edu.pl.pokemon_api.exception;

public class EntityNotFound extends RuntimeException {
    public EntityNotFound() {
        super("Entity not found.");
    }
}
