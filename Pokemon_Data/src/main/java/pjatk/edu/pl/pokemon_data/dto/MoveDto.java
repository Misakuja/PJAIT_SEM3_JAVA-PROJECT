package pjatk.edu.pl.pokemon_data.dto;

public record MoveDto(
        Long id,
        Integer apiId,
        String name,
        Integer power,
        Integer accuracy,
        Integer pp,
        TypeDto type
){}
