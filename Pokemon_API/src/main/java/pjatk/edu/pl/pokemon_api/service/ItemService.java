package pjatk.edu.pl.pokemon_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_api.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_api.exception.EntityNotFound;

import pjatk.edu.pl.pokemon_data.entity.Item;
import pjatk.edu.pl.pokemon_data.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService extends BaseService<Item> {
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
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
        logger.info("Fetching item by API ID: {}", apiId);
        return itemRepository.findByApiId(apiId).orElseThrow(() -> {
            logger.warn("Item with API ID: {} not found.", apiId);
            return new EntityNotFound();
        });
    }

    public Item getItemByName(String name) {
        logger.info("Fetching item by name: {}", name);
        return itemRepository.findByName(name).orElseThrow(() -> {
            logger.warn("Item with name: {} not found.", name);
            return new EntityNotFound();
        });
    }

    private void checkIfItemAlreadyExists(Item item) {
        logger.info("Checking if item already exists: {}", item);
        Optional<Item> existingItem = itemRepository.findByName(item.getName());
        if (existingItem.isPresent()) {
            logger.warn("Item with name: {} already exists.", item.getName());
            throw new EntityAlreadyExists();
        }
        logger.info("Item with name: {} does not exist. Proceeding to add.", item.getName());
    }
}
