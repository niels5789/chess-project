package com.example.ChessProject.controller;

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
    public List<Tile> getAllBoards() {
        return tileRepository.findAll();
    }

    @ResponseBody
    @PutMapping("/board/{idvan}/{idnaar}")
    public List<Tile> changeBoard(@PathVariable(value = "idvan") int idvan, @PathVariable(value = "idnaar") int idnaar) {
        List<Tile> Tilelist = tileRepository.findAll();
        String Piece = Tilelist.get(idvan).getName();
        int x1 = -1, y1 = -1, x2 = -1, y2 = -1;

//        This value should be set to false if done testing
        boolean validMove = true;

        // get coordinates
        x1 = getX(idvan, Tilelist);
        y1 = getY(idvan, Tilelist);
        x2 = getX(idnaar, Tilelist);
        y2 = getY(idnaar, Tilelist);


//      check if the move is legal
        switch (Piece){
            case "Pawn": validMove = validPawnMove(x1, y1, x2, y2, idvan, idnaar, Tilelist); break;
            case "Rook": validMove = validRookMove(x1, y1, x2, y2, idvan, idnaar, Tilelist); break;
            case "Knight": validMove = validKnightMove(x1, y1, x2, y2, idvan, idnaar, Tilelist); break;
            case "Bishop": validMove = validBishopMove(x1, y1, x2, y2, idvan, idnaar, Tilelist); break;
            case "Queen": validMove = validQueenMove(x1, y1, x2, y2, idvan, idnaar, Tilelist); break;
            case "King": validMove = validKingMove(x1, y1, x2, y2, idvan, idnaar, Tilelist); break;
            default: break;
        }

        if (validMove) {
            String tempName = Tilelist.get(idvan).getName();
            int tempColor = Tilelist.get(idvan).getColor();

            Tilelist.get(idnaar).setName(tempName);
            Tilelist.get(idnaar).setColor(tempColor);

            Tilelist.get(idvan).setName("");
            
            for (Tile tile: Tilelist){tileRepository.save(tile);}
           
        }

        return tileRepository.findAll();
    }

    private boolean validKingMove(int x1, int y1, int x2, int y2, int idvan, int idnaar, List<Tile> tilelist) {
        boolean valid = false;

        if ((((x2-x1)*(x2-x1) <= 1 ) && ((y2-y1)*(y2-y1) <= 1))){
            valid = true;
        }

        return valid;
    }

    private boolean validQueenMove(int x1, int y1, int x2, int y2, int idvan, int idnaar, List<Tile> tilelist) {
        boolean valid = false;

        if((x1 == x2 || (y1 == y2)) || ((x2 - x1)*(x2 - x1)==(y2 - y1)*(y2 - y1))){
            valid = true;
        }

        return valid;
    }

    private boolean validBishopMove(int x1, int y1, int x2, int y2, int idvan, int idnaar, List<Tile> tilelist) {
        boolean valid = false;

        if((x2 - x1)*(x2 - x1)==(y2 - y1)*(y2 - y1)){
            valid = true;
        }

        return valid;
    }

    private boolean validKnightMove(int x1, int y1, int x2, int y2, int idvan, int idnaar, List<Tile> tilelist) {
        boolean valid = false;

            if (((x2 == x1 + 1) && ((y2 == y1 + 2) || (y2 == y1 - 2))) ||
                    ((x2 == x1 + 2) && ((y2 == y1 + 1) || (y2 == y1 - 1))) ||
                            ((x2 == x1 - 1) && ((y2 == y1 + 2) || (y2 == y1 - 2))) ||
                                    ((x2 == x1 - 2) && ((y2 == y1 + 1) || (y2 == y1 - 1))))
            {
                valid = true;
            }

        return valid;
    }

    private boolean validRookMove(int x1, int y1, int x2, int y2, int idvan, int idnaar, List<Tile> tilelist) {
        boolean valid = false;

        if( x1 == x2 || y1 == y2){
            valid = true;
        }

        return valid;
    }

    private boolean validPawnMove(int x1, int y1, int x2, int y2, int idvan, int idnaar, List<Tile> list) {
        boolean valid = false;
        int color = list.get(idvan).getColor();

        if((color == 0 && (x1 == x2 && y1 + 1 == y2))||(color == 1 && (x1 == x2 && y1 - 1 == y2))) {
            valid = true;
        }

        return valid;
    }

    private int getX(int id, List<Tile> list) {
        return list.get(id).getxCo();
    }

    private int getY(int id, List<Tile> list) {
        return list.get(id).getyCo();
    }



//Dit is mijn aangepaste code
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
}
