package pjatk.edu.pl.pokemon_data.dto;

public record MoveDto(
        Long databaseId,
        Integer Id,
        String name,
        Integer power,
        Integer accuracy,
        Integer pp,
        TypeDto type
){}
