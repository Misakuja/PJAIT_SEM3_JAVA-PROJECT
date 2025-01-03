package pjatk.edu.pl.pokemon_data.exception;

public class EntityNotFound extends RuntimeException {
    public EntityNotFound() {
        super("Entity not found.");
    }
}
