package pjatk.edu.pl.pokemon_data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemDto(
        Long databaseId,
        @JsonProperty("id") Integer apiId,
        String name
){}
