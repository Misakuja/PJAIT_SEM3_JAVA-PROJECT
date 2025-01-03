package pjatk.edu.pl.pokemon_client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pjatk.edu.pl.pokemon_client.service.ViewMoveService;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Type;

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
        List<Move> moves = viewMoveService.getAllMoves();

        model.addAttribute("entityType", "Move");
        model.addAttribute("entities", moves);
        return "displayList";
    }

    //get all types
    @GetMapping("/get/type")
    public String getAllMovesByTypeId(Model model) {
        model.addAttribute("entityType", "All Moves Of Type");
        model.addAttribute("type", new Type());
        return "findByIdForm";
    }

    @PostMapping("/get/type")
    public String getAllMovesByTypeId(@ModelAttribute Type type, Model model) {
        Long inputId = type.getId();
        model.addAttribute("entityType", "All Moves Of Type");
        model.addAttribute("entities", viewMoveService.getAllMovesByTypeId(inputId));
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

    //find by Id
    @GetMapping("/find/id")
    public String findByIdForm(Model model) {
        model.addAttribute("entityType", "Move");
        model.addAttribute("move", new Move());
        return "findByIdForm";
    }

    @PostMapping("/find/id")
    public String viewById(@ModelAttribute Move move, Model model) {
        Long inputId = move.getId();
        model.addAttribute("entityType", "Move");
        model.addAttribute("entities", viewMoveService.getMoveById(inputId));
        return "displayList";
    }

    //find by API Id
    @GetMapping("/find/apiId")
    public String findByApiIdForm(Model model) {
        model.addAttribute("entityType", "Move");
        model.addAttribute("move", new Move());
        return "findByApiIdForm";
    }

    @PostMapping("/find/apiId")
    public String viewByApiId(@ModelAttribute Move move, Model model) {
        Integer inputApiId = move.getApiId();
        model.addAttribute("entityType", "Move");
        model.addAttribute("entities", viewMoveService.getMoveByApiId(inputApiId));
        return "displayList";
    }

    //find by name
    @GetMapping("/find/name")
    public String findByNameForm(Model model) {
        model.addAttribute("entityType", "Move");
        model.addAttribute("move", new Move());
        return "findByNameForm";
    }

    @PostMapping("/find/name")
    public String viewByName(@ModelAttribute Move move, Model model) {
        String nameInput = move.getName();
        model.addAttribute("entityType", "Move");
        model.addAttribute("entities", viewMoveService.getMoveByName(nameInput));
        return "displayList";
    }

    //find by accuracy
    @GetMapping("/find/accuracy")
    public String findByAccuracyForm(Model model) {
        model.addAttribute("entityType", "Move");
        model.addAttribute("move", new Move());
        return "findByAccuracyForm";
    }

    @PostMapping("/find/accuracy")
    public String viewByAccuracy(@ModelAttribute Move move, Model model) {
        Integer accuracyInput = move.getAccuracy();
        model.addAttribute("entityType", "Move");
        model.addAttribute("entities", viewMoveService.getMoveByAccuracy(accuracyInput));
        return "displayList";
    }

    //find by power
    @GetMapping("/find/power")
    public String findByPowerForm(Model model) {
        model.addAttribute("entityType", "Move");
        model.addAttribute("move", new Move());
        return "findByPowerForm";
    }

    @PostMapping("/find/power")
    public String viewByPower(@ModelAttribute Move move, Model model) {
        Integer powerInput = move.getPower();
        model.addAttribute("entityType", "Move");
        model.addAttribute("entities", viewMoveService.getMoveByPower(powerInput));
        return "displayList";
    }

    //find by pp
    @GetMapping("/find/pp")
    public String findByPpForm(Model model) {
        model.addAttribute("entityType", "Move");
        model.addAttribute("move", new Move());
        return "findByPpForm";
    }

    @PostMapping("/find/pp")
    public String viewByPp(@ModelAttribute Move move, Model model) {
        Integer ppInput = move.getAccuracy();
        model.addAttribute("entityType", "Move");
        model.addAttribute("entities", viewMoveService.getMoveByPp(ppInput));
        return "displayList";
    }
}
