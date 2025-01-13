package pjatk.edu.pl.pokemon_data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Pokemon(Integer apiId, String name, Integer height, Integer weight, Integer baseExperience, List<Type> types, List<Ability> abilities, List<Move> moves) {
        this.apiId = apiId;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.baseExperience = baseExperience;
        this.types = types;
        this.abilities = abilities;
        this.moves = moves;
    }

    private Integer apiId; // PokeAPI ID
    private String name;
    private Integer height;
    private Integer weight;
    private Integer baseExperience;

    @ManyToMany
    private List<Type> types;

    @ManyToMany
    private List<Ability> abilities;

    @ManyToMany
    private List<Move> moves;
}
