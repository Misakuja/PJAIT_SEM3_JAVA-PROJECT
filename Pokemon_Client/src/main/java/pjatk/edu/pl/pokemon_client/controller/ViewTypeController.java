package pjatk.edu.pl.pokemon_client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pjatk.edu.pl.pokemon_client.service.ViewTypeService;
import pjatk.edu.pl.pokemon_data.entity.Type;

import java.util.List;

@Controller
@RequestMapping("/client/type")
public class ViewTypeController {
    private final ViewTypeService viewTypeService;

    public ViewTypeController(ViewTypeService viewTypeService) {
        this.viewTypeService = viewTypeService;
    }

    @GetMapping("")
    public String getAllAbilities(Model model) {
        List<Type> abilities = viewTypeService.getAllTypes();

        model.addAttribute("entityType", "Type");
        model.addAttribute("entities", abilities);
        return "displayList";
    }

    //delete
    @GetMapping("/delete")
    public String displayDeleteForm(Model model) {
        model.addAttribute("entityType", "Type");
        model.addAttribute("type", new Type());
        return "deleteForm";
    }

    @PostMapping("/delete")
    public String submitDeleteForm(Type type) {
        Long typeId = type.getId();
        this.viewTypeService.deleteType(typeId);
        return "redirect:/client/type";
    }

    //add
    @GetMapping("/add")
    public String displayAddForm(Model model) {
        model.addAttribute("entityType", "Type");
        model.addAttribute("type", new Type());
        return "addForm";
    }

    @PostMapping("/add")
    public String submitAddForm(@ModelAttribute Type type) {
        this.viewTypeService.addType(type);
        return "redirect:/client/type";
    }

    //update
    @GetMapping("/update")
    public String displayUpdateForm(Model model) {
        model.addAttribute("entityType", "Type");
        model.addAttribute("type", new Type());
        return "updateForm";
    }

    @PostMapping("/update")
    public String submitUpdateForm(@ModelAttribute Type type) {
        Long id = type.getId();

        this.viewTypeService.updateType(type, id);
        return "redirect:/client/type";
    }

    //find by id
    @GetMapping("/find/id")
    public String findByIdForm(Model model) {
        model.addAttribute("entityType", "Type");
        model.addAttribute("type", new Type());
        return "findByIdForm";
    }

    @PostMapping("/find/id")
    public String viewById(@ModelAttribute Type type, Model model) {
        Long inputId = type.getId();
        model.addAttribute("entityType", "Type");
        model.addAttribute("entities", viewTypeService.getTypeById(inputId));
        return "displayList";
    }

    //find by API id
    @GetMapping("/find/apiId")
    public String findByApiIdForm(Model model) {
        model.addAttribute("entityType", "Type");
        model.addAttribute("type", new Type());
        return "findByApiIdForm";
    }

    @PostMapping("/find/apiId")
    public String viewByApiId(@ModelAttribute Type type, Model model) {
        Integer inputApiId = type.getApiId();
        model.addAttribute("entityType", "Type");
        model.addAttribute("entities", viewTypeService.getTypeByApiId(inputApiId));
        return "displayList";
    }

    //find by name
    @GetMapping("/find/name")
    public String findByNameForm(Model model) {
        model.addAttribute("entityType", "Type");
        model.addAttribute("type", new Type());
        return "findByNameForm";
    }

    @PostMapping("/find/name")
    public String viewByName(@ModelAttribute Type type, Model model) {
        String nameInput = type.getName();
        model.addAttribute("entityType", "Type");
        model.addAttribute("entities", viewTypeService.getTypeByName(nameInput));
        return "displayList";
    }
}
