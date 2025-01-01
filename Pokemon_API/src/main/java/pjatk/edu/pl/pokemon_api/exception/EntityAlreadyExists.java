package pjatk.edu.pl.pokemon_api.exception;

public class EntityAlreadyExists extends RuntimeException {
    public EntityAlreadyExists() {
        super("Entity with the same credentials already exists.");
    }
}
