package pjatk.edu.pl.pokemon_client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pjatk.edu.pl.pokemon_client.service.ViewAbilityService;
import pjatk.edu.pl.pokemon_data.entity.Ability;

import java.util.List;


@Controller
@RequestMapping("/client/ability")
public class ViewAbilityController {
    private final ViewAbilityService viewAbilityService;

    public ViewAbilityController(ViewAbilityService viewAbilityService) {
        this.viewAbilityService = viewAbilityService;
    }

    @GetMapping("")
    public String getAllAbilities(Model model) {
        List<Ability> abilities = viewAbilityService.getAllAbilities();

        model.addAttribute("entityType", "Ability");
        model.addAttribute("entities", abilities);
        return "displayList";
    }

    //delete
    @GetMapping("/delete")
    public String displayDeleteForm(Model model) {
        model.addAttribute("entityType", "Ability");
        model.addAttribute("ability", new Ability());
        return "deleteForm";
    }

    @PostMapping("/delete")
    public String submitDeleteForm(Ability ability) {
        Long abilityId = ability.getId();
        this.viewAbilityService.deleteAbility(abilityId);
        return "redirect:/client/ability";
    }

    //add
    @GetMapping("/add")
    public String displayAddForm(Model model) {
        model.addAttribute("entityType", "Ability");
        model.addAttribute("ability", new Ability());
        return "addForm";
    }

    @PostMapping("/add")
    public String submitAddForm(@ModelAttribute Ability ability) {
        this.viewAbilityService.addAbility(ability);
        return "redirect:/client/ability";
    }

    //update
    @GetMapping("/update")
    public String displayUpdateForm(Model model) {
        model.addAttribute("entityType", "Ability");
        model.addAttribute("ability", new Ability());
        return "updateForm";
    }

    @PostMapping("/update")
    public String submitUpdateForm(@ModelAttribute Ability ability) {
        Long id = ability.getId();

        this.viewAbilityService.updateAbility(ability, id);
        return "redirect:/client/ability";
    }

    //find by apiId
    @GetMapping("/find/id")
    public String findByIdForm(Model model) {
        model.addAttribute("entityType", "Ability");
        model.addAttribute("ability", new Ability());
        return "findByIdForm";
    }

    @PostMapping("/find/id")
    public String viewById(@ModelAttribute Ability ability, Model model) {
        Long inputId = ability.getId();
        model.addAttribute("entityType", "Ability");
        model.addAttribute("entities", viewAbilityService.getAbilityById(inputId));
        return "displayList";
    }

    //find by API apiId
    @GetMapping("/find/apiId")
    public String findByApiIdForm(Model model) {
        model.addAttribute("entityType", "Ability");
        model.addAttribute("ability", new Ability());
        return "findByApiIdForm";
    }

    @PostMapping("/find/apiId")
    public String viewByApiId(@ModelAttribute Ability ability, Model model) {
        Integer inputApiId = ability.getApiId();
        model.addAttribute("entityType", "Ability");
        model.addAttribute("entities", viewAbilityService.getAbilityByApiId(inputApiId));
        return "displayList";
    }

    //find by name
    @GetMapping("/find/name")
    public String findByNameForm(Model model) {
        model.addAttribute("entityType", "Ability");
        model.addAttribute("ability", new Ability());
        return "findByNameForm";
    }

    @PostMapping("/find/name")
    public String viewByName(@ModelAttribute Ability ability, Model model) {
        String nameInput = ability.getName();
        model.addAttribute("entities", viewAbilityService.getAbilityByName(nameInput));
        return "displayList";
    }
}
