package com.example.ChessProject.controller;

import com.example.ChessProject.Model.Player.Player;
import com.example.ChessProject.Model.Tile.Tile;
import com.example.ChessProject.repository.GameRepository;
import com.example.ChessProject.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class GameController {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;
    @ResponseBody
    @PutMapping("/newGame")
    public List<Tile> newGame(@RequestBody Player player) {

        List<>

    }

}