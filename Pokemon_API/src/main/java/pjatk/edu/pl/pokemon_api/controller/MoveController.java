package pjatk.edu.pl.pokemon_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pjatk.edu.pl.pokemon_api.service.MoveService;
import pjatk.edu.pl.pokemon_data.entity.Move;

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
    public ResponseEntity<List<Move>> getAllMoves() {
        List<Move> moves = moveService.getAllMoves();
        return ResponseEntity.ok(moves);
    }

    @GetMapping("/get/type/{id}")
    public ResponseEntity<List<Move>> getAllMovesByTypeId(@PathVariable Long id) {
        List<Move> moves = moveService.getAllMovesByTypeId(id);
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

    @GetMapping("/apiId/{apiId}")
    public ResponseEntity<Move> getMoveByApiId(@PathVariable Integer apiId) {
        Move move = moveService.getMoveByApiId(apiId);
        return ResponseEntity.ok(move);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Move> getMoveByName(@PathVariable String name) {
        Move move = moveService.getMoveByName(name);
        return ResponseEntity.ok(move);
    }

    @GetMapping("/accuracy/{accuracy}")
    public ResponseEntity<List<Move>> getMoveByAccuracy(@PathVariable Integer accuracy) {
        List<Move> moves = moveService.getMoveByAccuracy(accuracy);
        return ResponseEntity.ok(moves);
    }

    @GetMapping("/power/{power}")
    public ResponseEntity<List<Move>> getMoveByPower(@PathVariable Integer power) {
        List<Move> moves = moveService.getMoveByPower(power);
        return ResponseEntity.ok(moves);
    }

    @GetMapping("/pp/{pp}")
    public ResponseEntity<List<Move>> getMoveByPp(@PathVariable Integer pp) {
        List<Move> moves = moveService.getMoveByPp(pp);
        return ResponseEntity.ok(moves);
    }
}
