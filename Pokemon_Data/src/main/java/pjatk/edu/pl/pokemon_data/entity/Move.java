package pjatk.edu.pl.pokemon_data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Move(Integer apiId, String name, Integer accuracy, Integer power, Integer pp, Type type) {
        this.apiId = apiId;
        this.name = name;
        this.accuracy = accuracy;
        this.power = power;
        this.pp = pp;
        this.type = type;
    }

    private Integer apiId; //API ID
    private String name;
    private Integer power;
    private Integer accuracy;
    private Integer pp;

    @ManyToOne
    private Type type;
}
