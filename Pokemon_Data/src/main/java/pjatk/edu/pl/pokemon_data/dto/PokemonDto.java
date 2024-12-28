package pjatk.edu.pl.pokemon_data.dto;

import java.util.List;

public record PokemonDto(
        Long id,
        Integer apiId,
        String name,
        Integer height,
        Integer weight,
        Integer baseExperience,
        List<TypeDto> types,
        List<AbilityDto> abilities,
        List<MoveDto> moves

){}
