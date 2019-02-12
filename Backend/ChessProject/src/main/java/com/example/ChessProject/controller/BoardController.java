package com.example.ChessProject.controller;

import com.example.ChessProject.Model.Board.Board;
import com.example.ChessProject.Model.Tile.Tile;
import com.example.ChessProject.repository.BoardRepository;
import com.example.ChessProject.repository.TileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BoardController {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    TileRepository tileRepository;

    @ResponseBody
    @GetMapping("/boards")
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @ResponseBody
    @PutMapping("/board/{idvan}/{idnaar}")
    public List<Tile> changeBoard(@PathVariable(value = "idvan") int idvan, @PathVariable(value = "idnaar") int idnaar) {
        List<Tile> Tilelist = tileRepository.findAll();

        String Piece = Tilelist.get(idvan).getName();
        if(Piece == "Koning"){
            if(idnaar == idvan + 1 || idnaar == idvan - 1 || idnaar == idvan + 7 || idnaar == idvan + 8 || idnaar == idvan + 9 || idnaar == idvan - 7 || idnaar == idvan - 8 || idnaar == idvan -9 ){
                //Omslachtig manier van data veranderen. Kan makkelijker
                String newName = Tilelist.get(idvan).getName();
                int newColor = Tilelist.get(idnaar).getColor();
                Tilelist.get(idvan).setName("");
                Tilelist.get(idvan).setColor(3);
                Tilelist.get(idnaar).setName(newName);
                Tilelist.get(idnaar).setColor(newColor);
                for(Tile tile : Tilelist) {
                    tileRepository.save(tile);
                }
            }
        }


        return tileRepository.findAll();

    }

    @ResponseBody
    @GetMapping("/resetBoard")
    public List<Tile> resetBoard() {
        Tile p = new Tile();
        List<Tile> tileList = p.startList();
        for (Tile tile : tileList) {
            tileRepository.save(tile);
        }
        return tileRepository.findAll();
}

    @ResponseBody
    @GetMapping("/newboard")
    public List<Tile> updateBoard() {
        if (!tileRepository.existsById(1)) {
        Tile p = new Tile();
        List<Tile> tileList = p.startList();
        for (Tile tile : tileList) {
            tileRepository.save(tile);
        }}

        List<Tile> aanpassing = tileRepository.findAll();

        aanpassing.get(1).setName("Paard");
        for(Tile tile : aanpassing ) {
            tileRepository.save(tile);
        }
        return tileRepository.findAll();
    }

}
