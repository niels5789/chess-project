package com.example.ChessProject.Model.Board;

import com.example.ChessProject.Model.piece.Piece;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany
    private List<Piece> piecelist = new ArrayList<>();

    public Board(){}

    public Board(int id) {
        this.id = id;
    }

    public Board(int id, List<Piece> boardPosition) {
        this.id = id;
        this.piecelist = boardPosition;
    }

    public List<Piece> getBoardPosition() {
        return piecelist;
    }

    public void setBoardPosition(List<Piece> boardPosition) {
        this.piecelist = boardPosition;
    }

    public int getId() {
        return id;
    }


}
