package com.example.ChessProject.controller;

import com.example.ChessProject.Model.Game.Game;
import com.example.ChessProject.Model.Game.GameHistory;
import com.example.ChessProject.Model.Player.Player;
import com.example.ChessProject.Model.Tile.Tile;
import com.example.ChessProject.repository.GameHistoryRepository;
import com.example.ChessProject.repository.GameRepository;
import com.example.ChessProject.repository.PlayerRepository;
import com.example.ChessProject.repository.TileRepository;
import com.example.ChessProject.service.gameMechanics.GameMechanics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GameController {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    TileRepository tileRepository;
    @Autowired
    GameHistoryRepository gameHistoryRepository;
    @Autowired
    GameMechanics gm;


    @ResponseBody
    @PostMapping("/getgamesplayer")
    public List<Game> getGamesPlayer(@RequestBody Player player) {
        return gameRepository.findLastFiveGames(player.getId());
    }

    @ResponseBody
    @PutMapping("/game/{idvan}/{idnaar}")
    public ResponseEntity<List<Tile>> changeBoard( @RequestBody Player player, @PathVariable(value = "idvan")
            int idvan, @PathVariable(value = "idnaar") int idnaar) throws Exception {

    Game g = gameRepository.findLastGamePlayer(player.getId());
    List<Tile> tempList = changeStringIntoList(g.getCurrentBoardPosition());
    if( !tempList.get(idvan).getName().equals("") && tempList.get(idvan).getColor() == gameRepository.findLastGamePlayer(player.getId()).getMoveCount() % 2
            && tempList.get(idvan).getColor() != tempList.get(idnaar).getColor() && idvan != idnaar){
        tempList = gm.makeMoveIfLegal(idvan, idnaar, g.getId(), tempList);
        
    } else {
        return ResponseEntity.badRequest().build();
    }
    tempList = returnLastGame(player);
    return ResponseEntity.ok(tempList);
}


    @ResponseBody
    @GetMapping("/getallgames")
    public List<Game> allgames() {
        return gameRepository.findAll();
    }
    @ResponseBody
    @GetMapping("/getallgamesplayer")
    public List<Game> getAllGamesPlayer(@RequestBody Player player) {
        return gameRepository.findByPlayer(player);
    }


    @ResponseBody
    @PostMapping("/getnewgame")
    public Game getNewGame(@RequestBody Player player) {
        Player pl = playerRepository.findByUsername(player.getUsername());
        Tile t = new Tile();
        String a = changeTilelistIntoString(t.startList());
        Game g = gameRepository.save(new Game(0, false, a, player));
        gameHistoryRepository.save(new GameHistory(0, a,"New Board", "New Board", "New Board",  g));
        return g;

    }
    @ResponseBody
    @PostMapping("/returnlastgame")
    public List<Tile> returnLastGame(@RequestBody Player player) {
        Game g = gameRepository.findLastGamePlayer(player.getId());
        List<Tile> list = changeStringIntoList(g.getCurrentBoardPosition());
        return list;
    }

    @ResponseBody
    @PostMapping("/getlastgameplayer")
    public Game getLastGamePlayer(@RequestBody Player player) {
        return gameRepository.findLastGamePlayer(player.getId());
    }

    @ResponseBody
    @GetMapping("/getcurrentposition")
    public List<Tile> getCurrentPosition() {
        List<Tile> currentPosition = tileRepository.findAll();
        String a = changeTilelistIntoString(currentPosition);

        return changeStringIntoList(a);
    }
    @ResponseBody
    @PutMapping("/stringintotilelist/{gameid}")
    public List<Tile> stringIntoTileList(@PathVariable("gameid") Integer gameid) {
        Game g = gameRepository.findById(gameid).get();
        List<Tile> returnlist = changeStringIntoList(g.getCurrentBoardPosition());
        return returnlist;
    }

    public String changeTilelistIntoString(List<Tile> tilelist) {
        StringBuilder str = new StringBuilder(1000);
        int x = 1;
        for (Tile tile : tilelist) {
            str.append("" + x + "");
            if (!tile.getName().equals("")) {
                if(tile.getName().equals("Knight")) {
                    str.append("N");
                } else {
                    str.append("" + tile.getName().substring(0, 1) + "");
                }
            } else {
                str.append("E");
            }
            str.append("" + tile.getColor() + "|");
            x++;
        }
        return str.toString();
    }

    public List<Tile> changeStringIntoList(String boardPosition) {
        StringBuilder strb = new StringBuilder(boardPosition);
        Tile tile = new Tile();
        List<Tile> tilelist = tile.startList();
        int y = 0;
        String nameVariabele = "";
        int colorVariabele = 20;
        for (int x = 0; x < strb.length(); x++) {
            Character nextCharFake = strb.charAt(x);
            String nextStringReal = nextCharFake.toString();
            if (nextStringReal.equals("|")) {
                tilelist.get(y).setName(nameVariabele);
                tilelist.get(y).setColor(colorVariabele);
                y++;
                continue;
            }
            int z = y + 1;
            if (nextStringReal.equals(z)) {
                continue;
            }
            switch (nextStringReal) {
                case "R": {
                    nameVariabele = "Rook";
                    continue;
                }
                case "K": {
                    nameVariabele = "King";
                    continue;
                }
                case "N": {
                    nameVariabele = "Knight";
                    continue;
                }
                case "B": {
                    nameVariabele = "Bishop";
                    continue;
                }
                case "Q": {
                    nameVariabele = "Queen";
                    continue;
                }
                case "P": {
                    nameVariabele = "Pawn";
                    continue;
                }
                case "E": {
                    nameVariabele = "";
                    continue;
                }
            }
            if (nextStringReal.equals("0")) {
                colorVariabele = 0;
                continue;
            } else if (nextStringReal.equals("1")) {
                colorVariabele = 1;
                continue;
            } else if (nextStringReal.equals("3")) {
                colorVariabele = 3;
                continue;
            }
        }
        return tilelist;
    }
}