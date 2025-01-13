package pjatk.edu.pl.pokemon_data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Item(Integer apiId, String name) {
        this.apiId = apiId;
        this.name = name;
    }

    private Integer apiId; //API ID
    private String name;
}
