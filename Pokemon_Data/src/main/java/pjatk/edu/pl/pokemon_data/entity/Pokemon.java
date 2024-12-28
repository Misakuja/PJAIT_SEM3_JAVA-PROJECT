package pjatk.edu.pl.pokemon_data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
