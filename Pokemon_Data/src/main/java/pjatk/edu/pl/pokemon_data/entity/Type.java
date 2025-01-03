package pjatk.edu.pl.pokemon_data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer apiId; //API ID
    private String name;

    @ManyToMany(mappedBy = "types")
    private List<Pokemon> pokemons;

    @OneToMany(mappedBy = "type")
    private List<Move> moves;
}
