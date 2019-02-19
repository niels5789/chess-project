package com.example.ChessProject.controller;

import com.example.ChessProject.Model.Tile.Tile;
import com.example.ChessProject.repository.BoardRepository;
import com.example.ChessProject.repository.TileRepository;
import com.example.ChessProject.service.gameMechanics.GameMechanics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BoardController {

    @Autowired
    GameMechanics gm;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    TileRepository tileRepository;

    @ResponseBody
    @GetMapping("/boards")
    public List<Tile> getAllBoards() {
        return tileRepository.findAll();
    }

    @ResponseBody
    @PutMapping("/board/{idvan}/{idnaar}")
    public List<Tile> changeBoard(@PathVariable(value = "idvan") int idvan, @PathVariable(value = "idnaar") int idnaar) {
        return gm.makeMoveIfLegal(idvan, idnaar);
    }

    @ResponseBody
    @GetMapping("/resetboard")
    public List<Tile> resetBoard() {
        Tile p = new Tile();
        List<Tile> tileList = p.startList();
        for (Tile tile : tileList) {
            tileRepository.save(tile);
        }
        return tileRepository.findAll();
    }
}