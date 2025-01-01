package pjatk.edu.pl.pokemon_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pjatk.edu.pl.pokemon_api.service.MoveService;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;

import java.util.List;

@RestController
@RequestMapping("/api/move")
public class MoveController {
    private final MoveService moveService;

    @Autowired
    public MoveController(MoveService moveService) {
        this.moveService = moveService;
    }

    @GetMapping
    public ResponseEntity<List<Move>> getAllItems() {
        List<Move> moves = moveService.getAllMoves();
        return ResponseEntity.ok(moves);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMove(@PathVariable Long id) {
        moveService.deleteMove(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> addMove(@RequestBody Move move) {
        moveService.addMove(move);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMove(@RequestBody Move move, @PathVariable Long id) {
        moveService.updateMove(move, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Move> getMoveById(@PathVariable Long id) {
        Move move = moveService.getMoveById(id);
        return ResponseEntity.ok(move);
    }
}
