package pjatk.edu.pl.pokemon_client.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pjatk.edu.pl.pokemon_data.entity.Type;

import java.util.List;

@Service
public class ViewTypeService extends ViewBaseService {
    public ViewTypeService(RestClient restClient) {
        super(restClient);
    }

    public List<Type> getAllTypes() {
        return getAllEntities("/type");
    }

    public void deleteType(Long id) {
        deleteEntity("/type/" + id, id);
    }

    public void addType(Type type) {
        addEntity("/type", type);
    }

    public void updateType(Type type, Long id) {
        updateEntity("/type/" + id, type, id);
    }

    public Type getTypeById(Long id) {
        return getEntityByField("/type/apiId/" + id, id, new ParameterizedTypeReference<>() {});
    }

    public Type getTypeByApiId(Integer apiId) {
        return getEntityByField("/type/apiId/" + apiId, apiId, new ParameterizedTypeReference<>() {});
    }

    public Type getTypeByName(String name) {
        return getEntityByField("/type/name/" + name, name, new ParameterizedTypeReference<>() {});
    }
}
