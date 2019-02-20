package com.example.ChessProject.Model.Tile;
import com.example.ChessProject.Model.Game.Game;

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
    private int xCo;
    private int yCo;

    public Tile(){}

    public Tile(int id) {
        this.id = id;
    }

    public Tile(int id, String name, int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Tile(int id, String name, int color, int xCo, int yCo) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.xCo = xCo;
        this.yCo = yCo;
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

    public int getxCo() {
        return xCo;
    }

    public int getyCo() {
        return yCo;
    }

    public List<Tile> startList() {
        List<Tile> list = new ArrayList<>();

        list.add(new Tile(1, "Rook", 0 ,1 ,1));
        list.add(new Tile(2, "Knight", 0, 2, 1));
        list.add(new Tile(3, "Bishop", 0, 3,1));
        list.add(new Tile(4, "King", 0,4,1));
        list.add(new Tile(5, "Queen", 0,5,1));
        list.add(new Tile(6, "Bishop", 0,6,1));
        list.add(new Tile(7, "Knight", 0,7,1));
        list.add(new Tile(8, "Rook", 0,8,1));

        for(int x = 9; x <17; x++)
        {
            list.add(new Tile(x, "Pawn", 0,x-8 ,2));
        }

        int t = 17;
        for (int row = 3; row < 7 ; row++){
            for(int col = 1; col < 9; col++){

                list.add(new Tile(t++, "", 3, col, row));
            }
        }

        for(int z = 49; z < 57; z++) {
            list.add(new Tile(z, "Pawn", 1,z-48,7));
        }
        list.add(new Tile(57, "Rook", 1,1,8));
        list.add(new Tile(58, "Knight", 1,2,8));
        list.add(new Tile(59, "Bishop", 1,3,8));
        list.add(new Tile(60, "King", 1,4,8));
        list.add(new Tile(61, "Queen", 1,5,8));
        list.add(new Tile(62, "Bishop", 1,6,8));
        list.add(new Tile(63, "Knight", 1,7,8));
        list.add(new Tile(64, "Rook", 1,8,8));

        return list;
    }

    public Tile clone(){
        return new Tile(this.id, this.name, this.color, this.xCo, this.yCo);
    }
}
