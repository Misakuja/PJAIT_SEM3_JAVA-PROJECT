package pjatk.edu.pl.pokemon_data.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import pjatk.edu.pl.pokemon_data.dto.ResponseDto.AbilityResponseDto;
import pjatk.edu.pl.pokemon_data.dto.ResponseDto.MoveResponseDto;
import pjatk.edu.pl.pokemon_data.dto.ResponseDto.TypeResponseDto;

import java.util.List;

public record PokemonDto(
        Long databaseId,
        @JsonProperty("id") Integer apiId,
        String name,
        Integer height,
        Integer weight,
        @JsonProperty("base_experience") Integer baseExperience,
        List<TypeResponseDto> types,
        List<AbilityResponseDto> abilities,
        List<MoveResponseDto> moves
) {}