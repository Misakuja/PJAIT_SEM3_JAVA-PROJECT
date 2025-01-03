package pjatk.edu.pl.pokemon_data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MoveDto(
        Long databaseId,
        @JsonProperty("id") Integer apiId,
        String name,
        Integer power,
        Integer accuracy,
        Integer pp,
        TypeDto type
){}
