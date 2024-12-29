package pjatk.edu.pl.pokemon_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.edu.pl.pokemon_data.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByApiId(Integer id);
}
