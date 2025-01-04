package pjatk.edu.pl.pokemon_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pjatk.edu.pl.pokemon_api.service.TypeService;
import pjatk.edu.pl.pokemon_data.entity.Type;

import java.util.List;

@RestController
@RequestMapping("/api/type")
public class TypeController {
    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public ResponseEntity<List<Type>> getAllTypes() {
        List<Type> types = typeService.getAllTypes();
        return ResponseEntity.ok(types);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> addType(@RequestBody Type type) {
        typeService.addType(type);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateType(@RequestBody Type type, @PathVariable Long id) {
        typeService.updateType(type, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable Long id) {
        Type type = typeService.getTypeById(id);
        return ResponseEntity.ok(type);
    }

    @GetMapping("/apiId/{apiId}")
    public ResponseEntity<Type> getTypeByApiId(@PathVariable Integer apiId) {
        Type type = typeService.getTypeByApiId(apiId);
        return ResponseEntity.ok(type);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Type> getTypeByName(@PathVariable String name) {
        Type type = typeService.getTypeByName(name);
        return ResponseEntity.ok(type);
    }
}
