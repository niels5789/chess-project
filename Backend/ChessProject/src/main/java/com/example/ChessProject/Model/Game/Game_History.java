package com.example.ChessProject.Model.Game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Game_History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int whichMove;
    String boardPosition;

    @ManyToOne
    private Game game;

    public Game_History(int id, int whichMove, Game game) {
        this.id = id;
        this.whichMove = whichMove;
        this.game = game;
    }

    public int getId() {
        return id;
    }

    public int getWhichMove() {
        return whichMove;
    }

    public void setWhichMove(int whichMove) {
        this.whichMove = whichMove;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
