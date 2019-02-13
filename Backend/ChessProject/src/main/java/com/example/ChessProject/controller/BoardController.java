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
        int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
        boolean validMove = false;


        // get coordinates
        x1 = getX(idvan, Tilelist);
        y1 = getY(idvan, Tilelist);
        x2 = getX(idnaar, Tilelist);
        y2 = getY(idnaar, Tilelist);



        // switch op piece type
        switch (Piece){
            case "Pawn": validMove = validPawnMove(x1, y1, x2, y2, idvan, idnaar, Tilelist); break;
            case "Rook": ; break;
            case "Knight": ; break;
            case "Bishop": ; break;
            case "Queen": ; break;
            case "King": ; break;
            default: break;
        }
        // isValidMove test
            // tile id -> coordinate
        // if true make move; else do nothing/give message



//        if(Piece.equals(validMove)){

                String newName = Tilelist.get(idvan).getName();
                int newColor = Tilelist.get(idnaar).getColor();
                Tilelist.get(idvan).setName("");
                Tilelist.get(idvan).setColor(3);
                Tilelist.get(idnaar).setName(newName);
                Tilelist.get(idnaar).setColor(newColor);
                for(Tile tile : Tilelist) {
                    tileRepository.save(tile);
                }

//        }

        return tileRepository.findAll();
    }

    private int getX(int id, List<Tile> list) {
        return list.get(id).getxCo();
    }

    private int getY(int id, List<Tile> list) {
        return list.get(id).getyCo();
    }

    private boolean validPawnMove(int x1, int y1, int x2, int y2, int idvan, int idnaar, List<Tile> list) {
        boolean valid = false;
        int color = list.get(idvan).getColor();


        if(color == 0) {
            if (y1 == y2 && x1 + 1 == x2) {
                valid = true;
            }
        }

        return valid;
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
