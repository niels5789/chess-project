package com.example.ChessProject.service.gameMechanics;

import com.example.ChessProject.Model.Game.Game;
import com.example.ChessProject.Model.Player.Player;
import com.example.ChessProject.Model.Tile.Tile;
import com.example.ChessProject.controller.BoardController;
import com.example.ChessProject.controller.GameController;
import com.example.ChessProject.repository.GameRepository;

import com.example.ChessProject.repository.TileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameMechanics {

    @Autowired
    TileRepository tileRepository;
    @Autowired
    GameController gameController;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    GameController getGameController;

    private int turnCounter = 1;

    public int getTurnCounter() {
        return turnCounter;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public GameMechanics() {
    }

    public List<Tile> makeMoveIfLegal(int idvan, int idnaar, int gameid, List<Tile> tileList){
//        List<Tile> tileList = tileRepository.findAll();

        boolean validMove = false;

//      check if the move is legal
        validMove = isValidMove(idvan, idnaar, tileList);

//      check if move will cause check
        boolean putSelfInCheck = selfCheck(idvan, idnaar, tileList);

//      make the move
        if (validMove && !putSelfInCheck) {makeMove(idvan, idnaar, tileList, gameid);}

        return tileRepository.findAll();
    }

    private boolean selfCheck(int idvan, int idnaar, List<Tile> tileList) {
        List<Tile> newList = new ArrayList<>();
        List<Tile> newOpponentList = new ArrayList<>();
        int idKing = -11;

//        create clone list
        for(Tile tile: tileList){
            newList.add(tile.clone());
        }

//        get colors
        int playerColor = newList.get(idvan).getColor();
        int opponentColor = playerColor == 1 ? 0 : 1;

        for(int i = 0; i < newList.size(); i++) {
            if (newList.get(i).getName().equals("King") && newList.get(i).getColor() == playerColor) {
                idKing = i;
                break;
            }
        }

//        make move
        String tempName = newList.get(idvan).getName();
        int tempColor = newList.get(idvan).getColor();

        newList.get(idnaar).setName(tempName);
        newList.get(idnaar).setColor(tempColor);

        newList.get(idvan).setName("");


        for(int i = 0; i < newList.size(); i++) {
//            make tile list of enemy pieces
            if (!newList.get(i).getName().equals("") && newList.get(i).getColor() == opponentColor) {
                newOpponentList.add(newList.get(i));
            }
        }

        for(int i = 0; i < newList.size(); i++) {

//            find player king id
            if(newList.get(i).getName().equals("King") && newList.get(i).getColor() == playerColor){
                idKing = i;
                break;
            }
        }

//        for all opponents tiles check for legal move to king tile
        for(Tile tile: newOpponentList){
                if (isValidMove((tile.getId()-1), idKing, newList)) {
                   return true;}
        }

        return false;
    }

    private void makeMove(int idvan, int idnaar, List<Tile> tileList, int gameid) {

        String tempName = tileList.get(idvan).getName();
        int tempColor = tileList.get(idvan).getColor();

        tileList.get(idnaar).setName(tempName);
        tileList.get(idnaar).setColor(tempColor);

        tileList.get(idvan).setName("");
        tileList.get(idvan).setColor(3);

        turnCounter++;

//        String databaseString = gameController.changeTilelistIntoString(tileList);
        Game g =  gameRepository.findById(gameid).get();
        g.setCurrentBoardPosition(gameController.changeTilelistIntoString(tileList));
        gameRepository.save(g);
//        for (Tile tile: tileList){tileRepository.save(tile);}
    }

    private boolean isValidMove(int idvan, int idnaar, List<Tile> tileList) {

        int x1, y1, x2, y2;

        boolean validMove = false;

        if( idvan != idnaar && (tileList.get(idvan).getColor() != tileList.get(idnaar).getColor() || tileList.get(idnaar).getName().equals(""))) {


            // get coordinates
            x1 = getX(idvan, tileList);
            y1 = getY(idvan, tileList);
            x2 = getX(idnaar, tileList);
            y2 = getY(idnaar, tileList);

            String Piece = tileList.get(idvan).getName();


            switch (Piece) {
                case "Pawn":
                    validMove = validPawnMove(x1, y1, x2, y2, idvan, idnaar, tileList);
                    break;
                case "Rook":
                    validMove = validRookMove(x1, y1, x2, y2, idvan, idnaar, tileList);
                    break;
                case "Knight":
                    validMove = validKnightMove(x1, y1, x2, y2, idvan, idnaar, tileList);
                    break;
                case "Bishop":
                    validMove = validBishopMove(x1, y1, x2, y2, idvan, idnaar, tileList);
                    break;
                case "Queen":
                    validMove = validQueenMove(x1, y1, x2, y2, idvan, idnaar, tileList);
                    break;
                case "King":
                    validMove = validKingMove(x1, y1, x2, y2, idvan, idnaar, tileList);
                    break;
            }
        }

        return validMove;
    }

    private boolean validKingMove(int x1, int y1, int x2, int y2, int idvan, int idnaar, List<Tile> tileList) {
        boolean valid = false;

        Tile king = tileList.get(idvan);

        if ((((x2-x1)*(x2-x1) <= 1 ) && ((y2-y1)*(y2-y1) <= 1))){
            valid = true;
        } else if (king.getColor() == 0 && tileList.get(0).getName().equals("Rook") && tileList.get(1).getName().equals("") && tileList.get(2).getName().equals("") && idnaar == 1){

            for (int i = 1 ; i < 4 ; i++) {
                if (selfCheck(3, i, tileList)) {
                    valid = false;
                    break;
                } else {
                    valid = true;
                }
            }

            if(valid) {
                tileList.get(2).setName("Rook");
                tileList.get(2).setColor(0);

                tileList.get(0).setName("");
                tileList.get(0).setColor(3);
            }

        } else if(king.getColor() == 0 && tileList.get(7).getName().equals("Rook") && tileList.get(4).getName().equals("") && tileList.get(5).getName().equals("") && tileList.get(6).getName().equals("") && idnaar == 5){



            for (int i = 3 ; i < 6 ; i++) {
                if (selfCheck(3, i, tileList)) {
                    valid = false;
                    break;
                } else {
                    valid = true;
                }
            }

            if(valid){
                tileList.get(4).setName("Rook");
                tileList.get(4).setColor(0);

                tileList.get(7).setName("");
                tileList.get(7).setColor(3);
            }

        } else if (king.getColor() == 1 && tileList.get(56).getName().equals("Rook") && tileList.get(57).getName().equals("") && tileList.get(58).getName().equals("") && idnaar == 57){

            for (int i = 57 ; i < 60 ; i++) {
                if (selfCheck(59, i, tileList)) {
                    valid = false;
                    break;
                } else {
                    valid = true;
                }

            }

            if(valid){
                tileList.get(58).setName("Rook");
                tileList.get(58).setColor(1);

                tileList.get(56).setName("");
                tileList.get(56).setColor(3);
            }

    } else if(king.getColor() == 1 && tileList.get(63).getName().equals("Rook") && tileList.get(62).getName().equals("") && tileList.get(61).getName().equals("") && tileList.get(60).getName().equals("") && idnaar == 61){

            for (int i = 59 ; i < 62 ; i++) {
                if (selfCheck(59, i, tileList)) {
                    valid = false;
                    break;
                } else {
                    valid = true;
                }
            }

            if(valid){
                tileList.get(60).setName("Rook");
                tileList.get(60).setColor(1);

                tileList.get(63).setName("");
                tileList.get(63).setColor(3);
            }

    }

        return valid;
    }

    private boolean validQueenMove(int x1, int y1, int x2, int y2, int idvan, int idnaar, List<Tile> tilelist) {
        boolean valid = false;
        boolean pathIsFree = true;
        int distance;

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
        int distance;

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
                valid = true;
            }

//            if (color == 0 && (x2 == x1 -1 || x2 == x1 + 1)&& y2 == y1 + 2) {}

        } else { // taking enemy piece
            if (color == 0 && ((y2 == y1 + 1)&&( x2 == x1 - 1|| x2 == x1 + 1 )) || color == 1 && ((y2 == y1 - 1) && ( x2 == x1 - 1|| x2 == x1 + 1 ))){
                valid = true;
            }
        }


        return valid;
    }

    public List<Tile> promotePawn(Integer gameid, String piece) {
        Game g = gameRepository.findById(gameid).get();
        List<Tile> promoteList = gameController.changeStringIntoList(g.getCurrentBoardPosition());

        for( int i = 0; i < 8; i++){
            if(promoteList.get(i).getName().equals("Pawn")){
                promoteList.get(i).setName(piece);
                int count = g.getMoveCount();
                count++;
                g.setMoveCount(count);
                String str = gameController.changeTilelistIntoString(promoteList);
                g.setCurrentBoardPosition(str);
                gameRepository.save(g);
            }
        }

        for( int i = 56; i < 64; i++){
            if(promoteList.get(i).getName().equals("Pawn")){
                promoteList.get(i).setName(piece);
                int count = g.getMoveCount();
                count++;
                g.setMoveCount(count);
                String str = gameController.changeTilelistIntoString(promoteList);
                g.setCurrentBoardPosition(str);
                gameRepository.save(g);
            }
        }

        return promoteList;
    }

    private int getX(int id, List<Tile> list) {
        return list.get(id).getxCo();
    }

    private int getY(int id, List<Tile> list) {
        return list.get(id).getyCo();
    }

}
