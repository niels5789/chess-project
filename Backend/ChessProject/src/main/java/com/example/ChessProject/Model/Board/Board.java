package com.example.ChessProject.Model.Board;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "Board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private ArrayList<Tile> boardPosition = new ArrayList<>();

    public int getId() {
        return id;
    }

    public ArrayList<Tile> getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(ArrayList boardPosition) {
        this.boardPosition = boardPosition;
    }
}
