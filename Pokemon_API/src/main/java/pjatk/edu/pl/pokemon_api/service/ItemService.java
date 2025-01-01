package pjatk.edu.pl.pokemon_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_api.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_api.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.entity.Ability;
import pjatk.edu.pl.pokemon_data.entity.Item;
import pjatk.edu.pl.pokemon_data.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService extends BaseService<Item> {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository repository) {
        super(repository);
        this.itemRepository = repository;
    }

    public List<Item> getAllItems() {
        return getAllEntities();
    }

    public void deleteItem(Long id) {
        deleteEntity(id);
    }

    public void addItem(Item item) {
        checkIfItemAlreadyExists(item);
        addEntity(item);
    }

    public void updateItem(Item item, Long id) {
        checkIfItemAlreadyExists(item);
        updateEntity(item, id);
    }

    public Item getItemById(Long id) {
        return getEntityById(id);
    }

    public Item getItemByApiId(Integer apiId) {
        return itemRepository.findByApiId(apiId).orElseThrow(EntityNotFound::new);
    }

    public Item getItemByName(String name) {
        return itemRepository.findByName(name).orElseThrow(EntityNotFound::new);
    }

    private void checkIfItemAlreadyExists(Item item) {
        Optional<Item> existingItem = itemRepository.findByName(item.getName());
        if (existingItem.isPresent()) throw new EntityAlreadyExists();
    }
}
