package pjatk.edu.pl.pokemon_client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pjatk.edu.pl.pokemon_client.service.ViewMoveService;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Move;

import java.util.List;

@Controller
@RequestMapping("/client/move")
public class ViewMoveController {
    private final ViewMoveService viewMoveService;

    public ViewMoveController(ViewMoveService viewMoveService) {
        this.viewMoveService = viewMoveService;
    }

    @GetMapping("")
    public String getAllAbilities(Model model) {
        List<Move> abilities = viewMoveService.getAllMoves();

        model.addAttribute("entityType", "Move");
        model.addAttribute("entities", abilities);
        return "displayList";
    }

    //delete
    @GetMapping("/delete")
    public String displayDeleteForm(Model model) {
        model.addAttribute("entityType", "Move");
        model.addAttribute("move", new Move());
        return "deleteForm";
    }

    @PostMapping("/delete")
    public String submitDeleteForm(Move move) {
        Long moveId = move.getId();
        this.viewMoveService.deleteMove(moveId);
        return "redirect:/client/move";
    }

    //add
    @GetMapping("/add")
    public String displayAddForm(Model model) {
        model.addAttribute("entityType", "Move");
        model.addAttribute("move", new Move());
        return "addForm";
    }

    @PostMapping("/add")
    public String submitAddForm(@ModelAttribute Move move) {
        this.viewMoveService.addMove(move);
        return "redirect:/client/move";
    }

    //update
    @GetMapping("/update")
    public String displayUpdateForm(Model model) {
        model.addAttribute("entityType", "Move");
        model.addAttribute("move", new Move());
        return "updateForm";
    }

    @PostMapping("/update")
    public String submitUpdateForm(@ModelAttribute Move move) {
        Long id = move.getId();
        this.viewMoveService.updateMove(move, id);
        return "redirect:/client/move";
    }

    //find by id
    @GetMapping("/find/id")
    public String findByIdForm(Model model) {
        model.addAttribute("entityType", "Move");
        model.addAttribute("move", new Move());
        return "findByIdForm";
    }

    @PostMapping("/find/id")
    public String viewById(@ModelAttribute Move move, Model model) {
        Long inputId = move.getId();
        model.addAttribute("entities", viewMoveService.getMoveById(inputId));
        return "displayList";
    }
}
