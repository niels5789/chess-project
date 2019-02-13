package com.example.ChessProject.Model.Player;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

//    @NotNull
    String username;
//    @NotNull
    String password;

    String playerName;

    public Player() {
    }

    public Player(String username, String password, String playerName) {
        this.username = username;
        this.password = password;
        this.playerName = playerName;
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
