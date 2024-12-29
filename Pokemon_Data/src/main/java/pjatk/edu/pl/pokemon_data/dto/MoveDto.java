package pjatk.edu.pl.pokemon_data.dto;

public record MoveDto(
        Long databaseId,
        Integer id,
        String name,
        Integer power,
        Integer accuracy,
        Integer pp,
        TypeDto type
){}
