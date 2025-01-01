package pjatk.edu.pl.pokemon_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_data.entity.Ability;
import pjatk.edu.pl.pokemon_data.entity.Item;
import pjatk.edu.pl.pokemon_data.entity.Type;
import pjatk.edu.pl.pokemon_data.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService extends BaseService<Item> {
    @Autowired
    public ItemService(ItemRepository repository) {
        super(repository);
    }

    public List<Item> getAllItems() {
        return getAllEntities();
    }

    public void deleteItem(Long id) {
        deleteEntity(id);
    }

    public void addItem(Item item) {
        addEntity(item);
    }

    public void updateItem(Item item, Long id) {
        updateEntity(item, id);
    }

    public Item getItemById(Long id) {
        return getEntityById(id);
    }
}
