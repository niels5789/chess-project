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
    private Color color;

    public Piece(){}

    public Piece(int id) {
        this.id = id;
    }


    public Piece(int id, String name, Color color) {
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public List<Piece> startList() {
        List<Piece> list = new ArrayList<>();

        list.add(new Piece(1, "Toren", Color.WHITE));
        list.add(new Piece(2, "Paard", Color.WHITE));
        list.add(new Piece(3, "Loper", Color.WHITE));
        list.add(new Piece(4, "Koning", Color.WHITE));
        list.add(new Piece(5, "Koningin", Color.WHITE));
        list.add(new Piece(6, "Loper", Color.WHITE));
        list.add(new Piece(7, "Paard", Color.WHITE));
        list.add(new Piece(8, "Toren", Color.WHITE));

        for(int x = 9; x <17; x++)
        {
            list.add(new Piece(x, "Pion", Color.WHITE));
        }
        for(int y = 17; y < 49; y++) {
            list.add(new Piece(y));
        }
        for(int z = 49; z < 57; z++) {
            list.add(new Piece(z, "Pion", Color.BLACK));
        }
        list.add(new Piece(57, "Toren", Color.BLACK));
        list.add(new Piece(58, "Paard", Color.BLACK));
        list.add(new Piece(59, "Loper", Color.BLACK));
        list.add(new Piece(60, "Koning", Color.BLACK));
        list.add(new Piece(61, "Koningin", Color.BLACK));
        list.add(new Piece(62, "Loper", Color.BLACK));
        list.add(new Piece(63, "Paard", Color.BLACK));
        list.add(new Piece(64, "Toren", Color.BLACK));

        return list;
    }
}
