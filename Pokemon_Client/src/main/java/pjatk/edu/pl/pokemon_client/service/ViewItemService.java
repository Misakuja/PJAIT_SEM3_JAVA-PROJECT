package pjatk.edu.pl.pokemon_client.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pjatk.edu.pl.pokemon_data.entity.Item;

import java.util.List;

@Service
public class ViewItemService extends ViewBaseService {

    public ViewItemService(RestClient restClient) {
        super(restClient);
    }

    public List<Item> getAllItems() {
        return getAllEntities("/item");
    }

    public void deleteItem(Long id) {
        deleteEntity("/item/" + id, id);
    }

    public void addItem(Item item) {
        addEntity("/item/add", item);
    }

    public void updateItem(Item item, Long id) {
        updateEntity("/item/" + id, item, id);
    }

    public Item getItemById(Long id) {
        return getEntityById("/item/id/" + id, id, new ParameterizedTypeReference<Item>() {});
    }
}
