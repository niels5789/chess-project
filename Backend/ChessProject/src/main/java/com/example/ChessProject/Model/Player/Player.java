package com.example.ChessProject.Model.Player;

import com.example.ChessProject.Model.Game.Game;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotNull
    String username;
//    @NotNull
    String password;

    String playerName;

   @OneToMany(mappedBy = "player")
   private List<PlayerGame> playerGame = new ArrayList<>();

    public Player() {
    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Player(int id, String username, String password, String playerName, List<PlayerGame> playerGame) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.playerName = playerName;
        this.playerGame = playerGame;
    }

    public List<PlayerGame> getPlayerGame() {
        return playerGame;
    }

    public void setPlayerGame(List<PlayerGame> playerGame) {
        this.playerGame = playerGame;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPlayerName() {
        return playerName;
    }

}
