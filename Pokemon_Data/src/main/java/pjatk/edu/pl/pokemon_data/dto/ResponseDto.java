package pjatk.edu.pl.pokemon_data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseDto {
    private String next;
    private String previous;
    private List<ResultDto> results;
}
