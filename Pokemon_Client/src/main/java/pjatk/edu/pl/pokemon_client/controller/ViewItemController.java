package pjatk.edu.pl.pokemon_client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pjatk.edu.pl.pokemon_client.service.ViewItemService;
import pjatk.edu.pl.pokemon_data.entity.Item;

import java.util.List;

@Controller
@RequestMapping("/client/item")
public class ViewItemController {
    private final ViewItemService viewItemService;

    public ViewItemController(ViewItemService viewItemService) {
        this.viewItemService = viewItemService;
    }

    @GetMapping("")
    public String getAllAbilities(Model model) {
        List<Item> abilities = viewItemService.getAllItems();

        model.addAttribute("entityType", "Item");
        model.addAttribute("entities", abilities);
        return "displayList";
    }

    //delete
    @GetMapping("/delete")
    public String displayDeleteForm(Model model) {
        model.addAttribute("entityType", "Item");
        model.addAttribute("item", new Item());
        return "deleteForm";
    }

    @PostMapping("/delete")
    public String submitDeleteForm(Item item) {
        Long itemId = item.getId();
        this.viewItemService.deleteItem(itemId);
        return "redirect:/client/item";
    }

    //add
    @GetMapping("/add")
    public String displayAddForm(Model model) {
        model.addAttribute("entityType", "Item");
        model.addAttribute("item", new Item());
        return "addForm";
    }

    @PostMapping("/add")
    public String submitAddForm(@ModelAttribute Item item) {
        this.viewItemService.addItem(item);
        return "redirect:/client/item";
    }

    //update
    @GetMapping("/update")
    public String displayUpdateForm(Model model) {
        model.addAttribute("entityType", "Item");
        model.addAttribute("item", new Item());
        return "updateForm";
    }

    @PostMapping("/update")
    public String submitUpdateForm(@ModelAttribute Item item) {
        Long id = item.getId();
        this.viewItemService.updateItem(item, id);
        return "redirect:/client/item";
    }

    //find by id
    @GetMapping("/find/id")
    public String findByIdForm(Model model) {
        model.addAttribute("entityType", "Item");
        model.addAttribute("item", new Item());
        return "findByIdForm";
    }

    @PostMapping("/find/id")
    public String viewById(@ModelAttribute Item item, Model model) {
        Long inputId = item.getId();
        model.addAttribute("entities", viewItemService.getItemById(inputId));
        return "displayList";
    }
}
