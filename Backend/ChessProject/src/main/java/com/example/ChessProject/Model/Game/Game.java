package com.example.ChessProject.Model.Game;
import com.example.ChessProject.Model.Player.Player;
import com.example.ChessProject.Model.Player.PlayerGame;
import com.example.ChessProject.Model.Tile.Tile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    int moveCount;
    boolean isFinished;
    @Column(length = 500)
    String currentBoardPosition;

    @OneToMany(mappedBy = "game")
    private List<PlayerGame> playerGame = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.ALL})
    Game_History game_history;

    public Game() {
    }

    public Game( String currentBoardPosition, int moveCount, boolean isFinished) {
        this.currentBoardPosition = currentBoardPosition;
        this.moveCount = moveCount;
        this.isFinished = isFinished;
    }

    public Game(int moveCount, boolean isFinished, String currentBoardPosition, List<PlayerGame> playerGame) {
        this.moveCount = moveCount;
        this.isFinished = isFinished;
        this.currentBoardPosition = currentBoardPosition;
        this.playerGame = playerGame;
    }

    public int getId() {
        return id;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Game_History getGame_history() {
        return game_history;
    }

    public void setGame_history(Game_History game_history) {
        this.game_history = game_history;
    }

    public String getCurrentBoardPosition() {
        return currentBoardPosition;
    }

    public void setCurrentBoardPosition(String currentBoardPosition) {
        this.currentBoardPosition = currentBoardPosition;
    }

    public List<PlayerGame> getPlayerGame() {
        return playerGame;
    }

    public void setPlayerGame(List<PlayerGame> playerGame) {
        this.playerGame = playerGame;
    }
}




