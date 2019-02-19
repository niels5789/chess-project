package com.example.ChessProject.controller;

import com.example.ChessProject.Model.Tile.Tile;
import com.example.ChessProject.repository.TileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BoardController {

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

        boolean validMove = false;

//      check if the move is legal
        if( idvan != idnaar && (Tilelist.get(idvan).getColor() != Tilelist.get(idnaar).getColor() || Tilelist.get(idnaar).getName().equals(""))) {

            // get coordinates
            x1 = getX(idvan, Tilelist);
            y1 = getY(idvan, Tilelist);
            x2 = getX(idnaar, Tilelist);
            y2 = getY(idnaar, Tilelist);

            switch (Piece) {
                case "Pawn":
                    validMove = validPawnMove(x1, y1, x2, y2, idvan, idnaar, Tilelist);
                    break;
                case "Rook":
                    validMove = validRookMove(x1, y1, x2, y2, idvan, idnaar, Tilelist);
                    break;
                case "Knight":
                    validMove = validKnightMove(x1, y1, x2, y2, idvan, idnaar, Tilelist);
                    break;
                case "Bishop":
                    validMove = validBishopMove(x1, y1, x2, y2, idvan, idnaar, Tilelist);
                    break;
                case "Queen":
                    validMove = validQueenMove(x1, y1, x2, y2, idvan, idnaar, Tilelist);
                    break;
                case "King":
                    validMove = validKingMove(x1, y1, x2, y2, idvan, idnaar, Tilelist);
                    break;
                default:
                    break;
            }
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
        boolean pathIsFree = true;
        int distance = 0;

//        if((x1 == x2 || (y1 == y2)) || ((x2 - x1)*(x2 - x1)==(y2 - y1)*(y2 - y1))){
//            valid = true;
//        }

        if((x2 - x1)*(x2 - x1)==(y2 - y1)*(y2 - y1)){

            if ( x2 > x1 && y2 > y1){
                distance = x2 - x1;

                for(int i = 1; i < distance ; i++){
                    if(!tilelist.get(idvan + (9 * i)).getName().equals("")){
                        pathIsFree = false;
                        break;
                    }
                }

            } else
            if ( x2 < x1 && y2 > y1){
                distance = x1 - x2;

                for(int i = 1; i < distance ; i++){
                    if(!tilelist.get(idvan + (7 * i)).getName().equals("")){
                        pathIsFree = false;
                        break;
                    }
                }
            } else

            if ( x2 > x1 && y2 < y1){
                distance = x2 - x1;

                for(int i = 1; i < distance ; i++){
                    if(!tilelist.get(idvan - (7 * i)).getName().equals("")){
                        pathIsFree = false;
                        break;
                    }
                }

            } else
            if ( x2 < x1 && y2 < y1){
                distance = x1 - x2;

                for(int i = 1; i < distance ; i++){
                    if(!tilelist.get(idvan - (9 * i)).getName().equals("")){
                        pathIsFree = false;
                        break;
                    }
                }
            }

            valid = true;
        }

        if(y1 == y2){
            if(x2 > x1) {
                distance = x2 - x1;
                for (int i = 1; i < distance; i++) {
                    if (!tilelist.get(idvan + i).getName().equals("")) {
                        pathIsFree = false;
                        break;
                    }
                }
            } else if(x2 < x1) {
                distance = x1 - x2;
                for (int i = 1; i < distance; i++) {
                    if (!tilelist.get(idvan - i).getName().equals("")) {
                        pathIsFree = false;
                        break;
                    }
                }
            }

            valid = true;
        }

        if( x1 == x2){
            if(y2 > y1) {
                distance = y2 - y1;
                for (int i = 1; i < distance; i++) {
                    if (!tilelist.get(idvan + (i * 8)).getName().equals("")) {
                        pathIsFree = false;
                        break;
                    }
                }
            } else if(y2 < y1) {
                distance = y1 - y2;
                for (int i = 1; i < distance; i++) {
                    if (!tilelist.get(idvan - (i * 8)).getName().equals("")) {
                        pathIsFree = false;
                        break;
                    }
                }
            }

            valid = true;
        }

        if (!pathIsFree){valid = false;}
        return valid;
    }

    private boolean validBishopMove(int x1, int y1, int x2, int y2, int idvan, int idnaar, List<Tile> tilelist) {
        boolean valid = false;
        boolean pathIsFree = true;
        int distance = 0;

        if((x2 - x1)*(x2 - x1)==(y2 - y1)*(y2 - y1)){

            if ( x2 > x1 && y2 > y1){
                distance = x2 - x1;

                for(int i = 1; i < distance ; i++){
                    if(!tilelist.get(idvan + (9 * i)).getName().equals("")){
                        pathIsFree = false;
                        break;
                    }
                }

            } else
            if ( x2 < x1 && y2 > y1){
                distance = x1 - x2;

                for(int i = 1; i < distance ; i++){
                    if(!tilelist.get(idvan + (7 * i)).getName().equals("")){
                        pathIsFree = false;
                        break;
                    }
                }
            } else

            if ( x2 > x1 && y2 < y1){
                distance = x2 - x1;

                for(int i = 1; i < distance ; i++){
                    if(!tilelist.get(idvan - (7 * i)).getName().equals("")){
                        pathIsFree = false;
                        break;
                    }
                }

            } else
            if ( x2 < x1 && y2 < y1){
                distance = x1 - x2;

                for(int i = 1; i < distance ; i++){
                    if(!tilelist.get(idvan - (9 * i)).getName().equals("")){
                        pathIsFree = false;
                        break;
                    }
                }
            }

            valid = true;
        }



        if (!pathIsFree){valid = false;}
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
        boolean pathIsFree = true;
        int distance = 0;

        if(y1 == y2){
            if(x2 > x1) {
                distance = x2 - x1;
                for (int i = 1; i < distance; i++) {
                    if (!tilelist.get(idvan + i).getName().equals("")) {
                        pathIsFree = false;
                        break;
                    }
                }
            } else if(x2 < x1) {
                distance = x1 - x2;
                for (int i = 1; i < distance; i++) {
                    if (!tilelist.get(idvan - i).getName().equals("")) {
                        pathIsFree = false;
                        break;
                    }
                }
            }

            valid = true;
        }

        if( x1 == x2){
            if(y2 > y1) {
                distance = y2 - y1;
                for (int i = 1; i < distance; i++) {
                    if (!tilelist.get(idvan + (i * 8)).getName().equals("")) {
                        pathIsFree = false;
                        break;
                    }
                }
            } else if(y2 < y1) {
                distance = y1 - y2;
                for (int i = 1; i < distance; i++) {
                    if (!tilelist.get(idvan - (i * 8)).getName().equals("")) {
                        pathIsFree = false;
                        break;
                    }
                }
            }

            valid = true;
        }

        if (!pathIsFree){valid = false;}

        return valid;
    }

    private boolean validPawnMove(int x1, int y1, int x2, int y2, int idvan, int idnaar, List<Tile> tilelist) {
        boolean valid = false;
        int color = tilelist.get(idvan).getColor();


        if(tilelist.get(idnaar).getName().equals("")) {
            // basic 1 step move
            if ((color == 0 && (x1 == x2 && y1 + 1 == y2)) || (color == 1 && (x1 == x2 && y1 - 1 == y2))) {
                valid = true;
            }

            // double step move
            if ((color == 0 && (x1 == x2 && y1 + 2 == y2 && y1 == 2)) && tilelist.get(idvan + 8).getName().equals("")) {
                valid = true;
            } else if ((color == 1 && (x1 == x2 && y1 - 2 == y2 && y1 == 7)) && tilelist.get(idvan - 8).getName().equals("")){
                System.out.println("in de methode");
                valid = true;
            }

        } else {
            if (color == 0 && ((y2 == y1 + 1)&&( x2 == x1 - 1|| x2 == x1 + 1 )) || color == 1 && ((y2 == y1 - 1) && ( x2 == x1 - 1|| x2 == x1 + 1 ))){
                valid = true;
            }
        }

        return valid;
    }

    private int getX(int id, List<Tile> list) {
        return list.get(id).getxCo();
    }

    private int getY(int id, List<Tile> list) {
        return list.get(id).getyCo();
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