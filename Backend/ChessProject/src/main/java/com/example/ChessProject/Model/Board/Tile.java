package com.example.ChessProject.Model.Board;

import com.example.ChessProject.Model.piece.Piece;

import javax.persistence.*;

@Entity
@Table(name = "Tile")
public class Tile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Piece piece;
    private int xCoordinate;
    private int yCoordinate;

    public Tile() {
    }

    public Tile(int id, Piece piece, int xCoordinate, int yCoordinate) {
        this.id = id;
        this.piece = piece;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
