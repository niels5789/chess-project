package com.example.ChessProject.Model.Tile;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Tile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int color;



    public Tile(){}

    public Tile(int id) {
        this.id = id;
    }


    public Tile(int id, String name, int color) {
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
    
    public List<Tile> startList() {
        List<Tile> list = new ArrayList<>();

        list.add(new Tile(1, "Toren", 0));
        list.add(new Tile(2, "Paard", 0));
        list.add(new Tile(3, "Loper", 0));
        list.add(new Tile(4, "Koning", 0));
        list.add(new Tile(5, "Koningin", 0));
        list.add(new Tile(6, "Loper", 0));
        list.add(new Tile(7, "Paard", 0));
        list.add(new Tile(8, "Toren", 0));

        for(int x = 9; x <17; x++)
        {
            list.add(new Tile(x, "Pion", 0));
        }
        for(int y = 17; y < 49; y++) {
            list.add(new Tile(y));
        }
        for(int z = 49; z < 57; z++) {
            list.add(new Tile(z, "Pion", 1));
        }
        list.add(new Tile(57, "Toren", 1));
        list.add(new Tile(58, "Paard", 1));
        list.add(new Tile(59, "Loper", 1));
        list.add(new Tile(60, "Koning", 1));
        list.add(new Tile(61, "Koningin", 1));
        list.add(new Tile(62, "Loper", 1));
        list.add(new Tile(63, "Paard", 1));
        list.add(new Tile(64, "Toren", 1));

        return list;
    }
}
