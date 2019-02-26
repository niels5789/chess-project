package com.example.ChessProject.Model.Game;

import com.example.ChessProject.Model.Player.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    int moveCount;
    boolean isFinished;
    String gameStatus;
    @Column(length = 500)
    String currentBoardPosition;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;



    public Game() {
    }

    public Game(int moveCount, boolean isFinished, String currentBoardPosition, Player player) {
        this.moveCount = moveCount;
        this.isFinished = isFinished;
        this.currentBoardPosition = currentBoardPosition;
        this.player = player;
        this.gameStatus = "Active";
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

    public String getCurrentBoardPosition() {
        return currentBoardPosition;
    }

    public void setCurrentBoardPosition(String currentBoardPosition) {
        this.currentBoardPosition = currentBoardPosition;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }
}




