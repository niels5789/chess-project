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
    private int xCo;
    private int yCo;



    public Tile(){}

    public Tile(int id) {
        this.id = id;
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

        list.add(new Tile(1, "Toren", 0 ,1 ,1));
        list.add(new Tile(2, "Paard", 0, 2, 1));
        list.add(new Tile(3, "Loper", 0, 3,1));
        list.add(new Tile(4, "Koning", 0,4,1));
        list.add(new Tile(5, "Koningin", 0,5,1));
        list.add(new Tile(6, "Loper", 0,6,1));
        list.add(new Tile(7, "Paard", 0,7,1));
        list.add(new Tile(8, "Toren", 0,8,1));

        for(int x = 9; x <17; x++)
        {
            list.add(new Tile(x, "Pion", 0,x-8 ,2));
        }


//        for(int y = 17; y < 49; y++) {
//            list.add(new Tile(y));
//        }

        for (int row = 3; row < 7 ; row++){
            for(int col = 1; col < 9; col++){
                int t = 17;
                list.add(new Tile(t++, null, 0, col, row));
            }
        }

        for(int z = 49; z < 57; z++) {
            list.add(new Tile(z, "Pion", 1,z-48,7));
        }
        list.add(new Tile(57, "Toren", 1,1,8));
        list.add(new Tile(58, "Paard", 1,2,8));
        list.add(new Tile(59, "Loper", 1,3,8));
        list.add(new Tile(60, "Koning", 1,4,8));
        list.add(new Tile(61, "Koningin", 1,5,8));
        list.add(new Tile(62, "Loper", 1,6,8));
        list.add(new Tile(63, "Paard", 1,7,8));
        list.add(new Tile(64, "Toren", 1,8,8));

        return list;
    }
}
