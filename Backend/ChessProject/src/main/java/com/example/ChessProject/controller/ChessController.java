package com.example.ChessProject.controller;

import com.example.ChessProject.exception.ResourceNotFoundException;
import com.example.ChessProject.Model.Game;
import com.example.ChessProject.repository.ChessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
//@RequestMapping("/api")

public class ChessController {

    @Autowired
    ChessRepository chessRepository;

    // Get All Notes
    @GetMapping("/game")
    public List<Game> getAllGames() {
        return chessRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/games")
    public Game createGame(@Valid @RequestBody Game game) {
        return chessRepository.save(game);
    }

    // Get a Single Note
    @GetMapping("/games/{id}")
    public Game getGameById(@PathVariable(value = "id") int gameId) {
        return chessRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game", "id", gameId));
    }

    // Update a Note
    @PutMapping("/games/{id}")
    public Game updateGame(@PathVariable(value = "id") int gameId,
                           @Valid @RequestBody Game gameDetails) {

        Game game = chessRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game", "id", gameId));

//        game.setTitle(gameDetails.getTitle());
//        game.setContent(gameDetails.getContent());

        Game updatedGame = chessRepository.save(game);
        return updatedGame;
    }

    // Delete a Note
    @DeleteMapping("/games/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable(value = "id") int gameId) {
        Game game = chessRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", gameId));

        chessRepository.delete(game);

        return ResponseEntity.ok().build();
    }
}