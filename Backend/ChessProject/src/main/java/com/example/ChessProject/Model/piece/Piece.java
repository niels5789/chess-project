package com.example.ChessProject.Model.piece;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int color;

    public Piece(){}

    public Piece(int id) {
        this.id = id;
    }


    public Piece(int id, String name, int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    
    public List<Piece> startList() {
        List<Piece> list = new ArrayList<>();

        list.add(new Piece(1, "Toren", 0));
        list.add(new Piece(2, "Paard", 0));
        list.add(new Piece(3, "Loper", 0));
        list.add(new Piece(4, "Koning", 0));
        list.add(new Piece(5, "Koningin", 0));
        list.add(new Piece(6, "Loper", 0));
        list.add(new Piece(7, "Paard", 0));
        list.add(new Piece(8, "Toren", 0));

        for(int x = 9; x <17; x++)
        {
            list.add(new Piece(x, "Pion", 0));
        }
        for(int y = 17; y < 49; y++) {
            list.add(new Piece(y));
        }
        for(int z = 49; z < 57; z++) {
            list.add(new Piece(z, "Pion", 1));
        }
        list.add(new Piece(57, "Toren", 1));
        list.add(new Piece(58, "Paard", 1));
        list.add(new Piece(59, "Loper", 1));
        list.add(new Piece(60, "Koning", 1));
        list.add(new Piece(61, "Koningin", 1));
        list.add(new Piece(62, "Loper", 1));
        list.add(new Piece(63, "Paard", 1));
        list.add(new Piece(64, "Toren", 1));

        return list;
    }
}
