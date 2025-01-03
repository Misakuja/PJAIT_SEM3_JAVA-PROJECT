package pjatk.edu.pl.pokemon_client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pjatk.edu.pl.pokemon_client.service.ViewPokemonService;
import pjatk.edu.pl.pokemon_data.entity.Ability;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.entity.Type;

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

    //get all abilities
    @GetMapping("/get/ability")
    public String getAllAbilitiesByPokemonId(Model model) {
        model.addAttribute("entityType", "All Abilities Of Pokemon");
        model.addAttribute("ability", new Ability());
        return "findByIdForm";
    }

    @PostMapping("/get/ability")
    public String getAllAbilitiesByPokemonId(@ModelAttribute Ability ability, Model model) {
        Long inputId = ability.getId();
        model.addAttribute("entityType", "All Abilities Of Pokemon");
        model.addAttribute("entities", viewPokemonService.getAllAbilitiesByPokemonId(inputId));
        return "displayList";
    }

    //get all moves
    @GetMapping("/get/move")
    public String getAllMovesByPokemonId(Model model) {
        model.addAttribute("entityType", "All Moves Of Pokemon");
        model.addAttribute("move", new Move());
        return "findByIdForm";
    }

    @PostMapping("/get/move")
    public String getAllMovesByPokemonId(@ModelAttribute Move move, Model model) {
        Long inputId = move.getId();
        model.addAttribute("entityType", "All Moves Of Pokemon");
        model.addAttribute("entities", viewPokemonService.getAllMovesByPokemonId(inputId));
        return "displayList";
    }

    //get all types
    @GetMapping("/get/type")
    public String getAllTypesByPokemonId(Model model) {
        model.addAttribute("entityType", "All Types Of Pokemon");
        model.addAttribute("type", new Type());
        return "findByIdForm";
    }

    @PostMapping("/get/type")
    public String getAllTypesByPokemonId(@ModelAttribute Type type, Model model) {
        Long inputId = type.getId();
        model.addAttribute("entityType", "All Types Of Pokemon");
        model.addAttribute("entities", viewPokemonService.getAllTypesByPokemonId(inputId));
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

    //find by apiId
    @GetMapping("/find/id")
    public String findByIdForm(Model model) {
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("pokemon", new Pokemon());
        return "findByIdForm";
    }

    @PostMapping("/find/id")
    public String viewById(@ModelAttribute Pokemon pokemon, Model model) {
        Long inputId = pokemon.getId();
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("entities", viewPokemonService.getPokemonById(inputId));
        return "displayList";
    }

    //find by API apiId
    @GetMapping("/find/apiId")
    public String findByApiIdForm(Model model) {
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("pokemon", new Pokemon());
        return "findByApiIdForm";
    }

    @PostMapping("/find/apiId")
    public String viewByApiId(@ModelAttribute Pokemon pokemon, Model model) {
        Integer inputApiId = pokemon.getApiId();
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("entities", viewPokemonService.getPokemonByApiId(inputApiId));
        return "displayList";
    }

    //find by name
    @GetMapping("/find/name")
    public String findByNameForm(Model model) {
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("pokemon", new Pokemon());
        return "findByNameForm";
    }

    @PostMapping("/find/name")
    public String viewByName(@ModelAttribute Pokemon pokemon, Model model) {
        String nameInput = pokemon.getName();
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("entities", viewPokemonService.getPokemonByName(nameInput));
        return "displayList";
    }

    //find by base experience
    @GetMapping("/find/baseExperience")
    public String findByBaseExperienceForm(Model model) {
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("pokemon", new Pokemon());
        return "findByBaseExperienceForm";
    }

    @PostMapping("/find/baseExperience")
    public String viewByBaseExperience(@ModelAttribute Pokemon pokemon, Model model) {
        Integer baseExperienceInput = pokemon.getBaseExperience();
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("entities", viewPokemonService.getPokemonByBaseExperience(baseExperienceInput));
        return "displayList";
    }

    //find by height
    @GetMapping("/find/height")
    public String findByHeightForm(Model model) {
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("pokemon", new Pokemon());
        return "findByHeightForm";
    }

    @PostMapping("/find/height")
    public String viewByHeight(@ModelAttribute Pokemon pokemon, Model model) {
        Integer heightInput = pokemon.getHeight();
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("entities", viewPokemonService.getPokemonByHeight(heightInput));
        return "displayList";
    }

    //find by weight
    @GetMapping("/find/weight")
    public String findByWeightForm(Model model) {
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("pokemon", new Pokemon());
        return "findByWeightForm";
    }

    @PostMapping("/find/weight")
    public String viewByWeight(@ModelAttribute Pokemon pokemon, Model model) {
        Integer weightInput = pokemon.getWeight();
        model.addAttribute("entityType", "Pokemon");
        model.addAttribute("entities", viewPokemonService.getPokemonByWeight(weightInput));
        return "displayList";
    }
}
