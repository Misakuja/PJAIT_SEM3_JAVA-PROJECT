package pjatk.edu.pl.pokemon_data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Ability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Ability(Integer apiId, String name) {
        this.name = name;
        this.apiId = apiId;
    }

    private Integer apiId; // API ID
    private String name;

}
