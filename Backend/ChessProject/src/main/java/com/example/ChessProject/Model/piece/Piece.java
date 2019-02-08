package com.example.ChessProject.Model.piece;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Table")
public abstract class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    private String name;
    @NotBlank
    private String color;

    public Piece(){}

    public Piece(int id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    //Used for pawn promotion
    public void setName(String name) {
        this.name = name;
    }
}
