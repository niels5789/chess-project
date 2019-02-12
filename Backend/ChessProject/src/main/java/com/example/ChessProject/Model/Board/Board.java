package com.example.ChessProject.Model.Board;

import com.example.ChessProject.Model.Tile.Tile;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany
    private List<Tile> tileList = new ArrayList<>();

    public Board(){}

    public Board(int id) {
        this.id = id;
    }

    public Board(int id, List<Tile> boardPosition) {
        this.id = id;
        this.tileList = boardPosition;
    }

    public List<Tile> getBoardPosition() {
        return tileList;
    }

    public void setBoardPosition(List<Tile> boardPosition) {
        this.tileList = boardPosition;
    }

    public int getId() {
        return id;
    }

}
