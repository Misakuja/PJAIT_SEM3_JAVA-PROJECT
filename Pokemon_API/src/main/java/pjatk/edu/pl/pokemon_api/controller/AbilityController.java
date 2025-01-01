package pjatk.edu.pl.pokemon_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pjatk.edu.pl.pokemon_api.service.AbilityService;
import pjatk.edu.pl.pokemon_data.entity.Ability;

import java.util.List;

@RestController
@RequestMapping("/api/ability")
public class AbilityController {
    private final AbilityService abilityService;

    @Autowired
    public AbilityController(AbilityService abilityService) {
        this.abilityService = abilityService;
    }

    @GetMapping
    public ResponseEntity<List<Ability>> getAllItems() {
        List<Ability> abilities = abilityService.getAllAbilities();
        return ResponseEntity.ok(abilities);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbility(@PathVariable Long id) {
        abilityService.deleteAbility(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> addAbility(@RequestBody Ability ability) {
        abilityService.addAbility(ability);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAbility(@RequestBody Ability ability, @PathVariable Long id) {
        abilityService.updateAbility(ability, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Ability> getAbilityById(@PathVariable Long id) {
        Ability ability = abilityService.getAbilityById(id);
        return ResponseEntity.ok(ability);
    }

    @GetMapping("/apiId/{apiId}")
    public ResponseEntity<Ability> getAbilityByApiId(@PathVariable Integer apiId) {
        Ability ability = abilityService.getAbilityByApiId(apiId);
        return ResponseEntity.ok(ability);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Ability> getAbilityByName(@PathVariable String name) {
        Ability ability = abilityService.getAbilityByName(name);
        return ResponseEntity.ok(ability);
    }


}
