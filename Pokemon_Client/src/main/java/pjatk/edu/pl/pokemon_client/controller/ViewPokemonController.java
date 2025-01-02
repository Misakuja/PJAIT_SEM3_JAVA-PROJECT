package pjatk.edu.pl.pokemon_client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pjatk.edu.pl.pokemon_client.service.ViewPokemonService;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;

import java.util.List;

@Controller
@RequestMapping("/client/pokemon")
public class ViewPokemonController {
    private final ViewPokemonService viewPokemonService;

    public ViewPokemonController(ViewPokemonService viewPokemonService) {
        this.viewPokemonService = viewPokemonService;
    }

    @GetMapping("")
    public String getAllAbilities(Model model) {
        List<Pokemon> abilities = viewPokemonService.getAllPokemons();

        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("entities", abilities);
        return "displayList";
    }

    //delete
    @GetMapping("/delete")
    public String displayDeleteForm(Model model) {
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("pokemon", new Pokemon());
        return "deleteForm";
    }

    @PostMapping("/delete")
    public String submitDeleteForm(Pokemon pokemon) {
        Long pokemonId = pokemon.getId();
        this.viewPokemonService.deletePokemon(pokemonId);
        return "redirect:/client/pokemon";
    }

    //add
    @GetMapping("/add")
    public String displayAddForm(Model model) {
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("pokemon", new Pokemon());
        return "addForm";
    }

    @PostMapping("/add")
    public String submitAddForm(@ModelAttribute Pokemon pokemon) {
        this.viewPokemonService.addPokemon(pokemon);
        return "redirect:/client/pokemon";
    }

    //update
    @GetMapping("/update")
    public String displayUpdateForm(Model model) {
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("pokemon", new Pokemon());
        return "updateForm";
    }

    @PostMapping("/update")
    public String submitUpdateForm(@ModelAttribute Pokemon pokemon) {
        Long id = pokemon.getId();

        this.viewPokemonService.updatePokemon(pokemon, id);
        return "redirect:/client/pokemon";
    }

    //find by id
    @GetMapping("/find/id")
    public String findByIdForm(Model model) {
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("pokemon", new Pokemon());
        return "findByIdForm";
    }

    @PostMapping("/find/id")
    public String viewById(@ModelAttribute Pokemon pokemon, Model model) {
        Long inputId = pokemon.getId();
        model.addAttribute("entities", viewPokemonService.getPokemonById(inputId));
        return "displayList";
    }
}
