package com.example.ChessProject.controller;

import com.example.ChessProject.Model.Game.Game;
import com.example.ChessProject.Model.Player.Player;
import com.example.ChessProject.Model.Tile.Tile;
import com.example.ChessProject.repository.GameRepository;
import com.example.ChessProject.repository.TileRepository;
import com.example.ChessProject.service.gameMechanics.GameMechanics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BoardController {
//    public static int turnCounter = 1;

    @Autowired
    GameMechanics gm;
    @Autowired
    TileRepository tileRepository;
    @Autowired
    GameRepository gameRepository;


    @ResponseBody
    @GetMapping("/boards")
    public List<Tile> getAllBoards() {
        return tileRepository.findAll();
    }

    @ResponseBody
    @PutMapping("/board/{idvan}/{idnaar}")
    public ResponseEntity<List<Tile>> changeBoard(@PathVariable(value = "idvan") int idvan, @PathVariable(value = "idnaar") int idnaar) throws Exception {
        List<Tile> tempList = tileRepository.findAll();

        if( !tempList.get(idvan).getName().equals("") && tempList.get(idvan).getColor() != gm.getTurnCounter() % 2 && tempList.get(idvan).getColor() != tempList.get(idnaar).getColor() && idvan != idnaar){
            tempList = gm.makeMoveIfLegal(idvan, idnaar, -42, tempList);

        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tempList);
    }

    @ResponseBody
    @GetMapping("/resetboard")
    public List<Tile> resetBoard() {
        gm.setTurnCounter(1);
        Tile p = new Tile();
        List<Tile> tileList = p.startList();
        for (Tile tile : tileList) {
            tileRepository.save(tile);
        }
        return tileRepository.findAll();
    }

    @ResponseBody
    @PostMapping("/promotion/{piece}")
    public List<Tile> promote(@RequestBody Player player, @PathVariable(value = "piece") String piece){
        Game g = gameRepository.findLastGamePlayer(player.getId());
        return gm.promotePawn(g.getId(), piece);
    }
}