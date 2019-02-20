package com.example.ChessProject.controller;

import com.example.ChessProject.Model.Tile.Tile;
import com.example.ChessProject.repository.TileRepository;
import com.example.ChessProject.service.gameMechanics.GameMechanics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BoardController {
    int turnCounter = 1;

    @Autowired
    GameMechanics gm;
    @Autowired
    TileRepository tileRepository;

    @ResponseBody
    @GetMapping("/boards")
    public List<Tile> getAllBoards() {
        return tileRepository.findAll();
    }

    @ResponseBody
    @PutMapping("/board/{idvan}/{idnaar}")
    public ResponseEntity<List<Tile>> changeBoard(@PathVariable(value = "idvan") int idvan, @PathVariable(value = "idnaar") int idnaar) throws Exception {
        List<Tile> tempList = tileRepository.findAll();

        if( !tempList.get(idvan).getName().equals("") && tempList.get(idvan).getColor() != turnCounter % 2) {
            tempList = gm.makeMoveIfLegal(idvan, idnaar);
            turnCounter++;
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tempList);
    }

    @ResponseBody
    @GetMapping("/resetboard")
    public List<Tile> resetBoard() {
        turnCounter = 1;
        Tile p = new Tile();
        List<Tile> tileList = p.startList();
        for (Tile tile : tileList) {
            tileRepository.save(tile);
        }
        return tileRepository.findAll();
    }

    @ResponseBody
    @GetMapping("/promotion/{piece}")
    public List<Tile> promote(@PathVariable(value = "piece") String piece){
        return gm.promotePawn(piece);
    }
}