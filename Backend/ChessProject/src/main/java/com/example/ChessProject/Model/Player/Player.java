package com.example.ChessProject.Model.Player;

import com.example.ChessProject.Model.Game.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotNull
    String username;
//    @NotNull
    String password;

    String playerName;

   @OneToMany(mappedBy = "player")
   private List<Game> games = new ArrayList<>();

    public Player() {
    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Player(String username, String password, String playerName) {
        this.username = username;
        this.password = password;
        this.playerName = playerName;
    }
    public Player(int id, String username, String password, String playerName, List<Game> games) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.playerName = playerName;
        this.games = games;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
