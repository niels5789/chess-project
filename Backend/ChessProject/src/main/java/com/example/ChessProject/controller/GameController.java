package com.example.ChessProject.controller;

import com.example.ChessProject.exception.ResourceNotFoundException;
import com.example.ChessProject.Model.Game;
import com.example.ChessProject.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
public class GameController {

    @Autowired
    GameRepository gameRepository;

    // Get All Games
    @ResponseBody
    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    // Create a new Game
    @ResponseBody
    @PostMapping("/games")
    public Game createGame(@Valid @RequestBody Game game) {
        return gameRepository.save(game);
    }

    // Get a Single Game
    @ResponseBody
    @GetMapping("/games/{id}")
    public Game getGameById(@PathVariable(value = "id") int gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game", "id", gameId));
    }

    // Update a Game
    @ResponseBody
    @PutMapping("/games/{id}")
    public Game updateGame(@PathVariable(value = "id") int gameId,
                           @Valid @RequestBody Game gameDetails) {

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Game", "id", gameId));

        Game updatedGame = gameRepository.save(game);
        return updatedGame;
    }

    // Delete a Game
    @ResponseBody
    @DeleteMapping("/games/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable(value = "id") int gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", gameId));

        gameRepository.delete(game);

        return ResponseEntity.ok().build();
    }
}