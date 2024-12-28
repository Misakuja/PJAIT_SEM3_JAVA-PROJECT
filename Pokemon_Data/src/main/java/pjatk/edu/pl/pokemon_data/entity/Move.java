package pjatk.edu.pl.pokemon_data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer apiId; //API ID
    private String name;
    private Integer power;
    private Integer accuracy;
    private Integer pp;

    @ManyToOne
    @JoinColumn(name = "type_id")  // foreign key from types, many moves one type
    private Type type;
}
