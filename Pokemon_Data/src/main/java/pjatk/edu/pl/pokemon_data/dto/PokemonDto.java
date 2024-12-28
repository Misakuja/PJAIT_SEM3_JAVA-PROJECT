package pjatk.edu.pl.pokemon_data.dto;
import java.util.List;

public record PokemonDto(
        Long databaseId,
        Integer id,
        String name,
        Integer height,
        Integer weight,
        Integer base_experience,
        List<TypeDto> types,
        List<AbilityDto> abilities,
        List<MoveDto> moves
) {}