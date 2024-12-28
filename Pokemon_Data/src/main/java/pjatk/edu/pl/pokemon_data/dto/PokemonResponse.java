package pjatk.edu.pl.pokemon_data.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PokemonResponse {
    private int count;

    private String next;

    private String previous;

    private List<PokemonDto> results;
}
