package com.example.ChessProject.Model.Game;
import com.example.ChessProject.Model.Player.Player;
import com.example.ChessProject.Model.Tile.Tile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int moveCount;
    boolean isFinished;


    public Game() {
    }

    public Game(int id, int moveCount, boolean isFinished) {
        this.id = id;
        this.moveCount = moveCount;
        this.isFinished = isFinished;
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

}




