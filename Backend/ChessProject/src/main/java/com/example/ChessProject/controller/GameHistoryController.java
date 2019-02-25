package com.example.ChessProject.controller;

import com.example.ChessProject.Model.Game.Game;
import com.example.ChessProject.Model.Game.GameHistory;
import com.example.ChessProject.repository.GameHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GameHistoryController {

    @Autowired
    GameHistoryRepository gameHistoryRepository;

    @ResponseBody
    @PostMapping("/getgamehistorylist")
    public List<GameHistory> listGameHistory(@RequestBody Game game) {
        return gameHistoryRepository.findHistoryFromGame(game.getId());
    }
}
