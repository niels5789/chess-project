package com.example.ChessProject.controller;

import com.example.ChessProject.Model.Game.Game;
import com.example.ChessProject.Model.Game.GameHistory;
import com.example.ChessProject.Model.Tile.Tile;
import com.example.ChessProject.repository.GameHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GameHistoryController {

    @Autowired
    GameHistoryRepository gameHistoryRepository;
    @Autowired
    GameController gameController;

    @ResponseBody
    @PostMapping("/getgamehistorylist")
    public List<GameHistory> listGameHistory(@RequestBody Game game) {
        return gameHistoryRepository.findHistoryFromGame(game.getId());
    }

    @ResponseBody
    @PutMapping("/gettilelistwithgameandmove/{movecountid}/{gameid}")
    public List<Tile> getTileListWithGameAndMove(@PathVariable(value = "movecountid") Integer movecountid, @PathVariable(value = "gameid") Integer gameid) {
        GameHistory gp = gameHistoryRepository.findHistoryWhereGameAndCountPlaceAreMatched(gameid, movecountid);
        return gameController.changeStringIntoList(gp.getBoardPosition());
    }
}
