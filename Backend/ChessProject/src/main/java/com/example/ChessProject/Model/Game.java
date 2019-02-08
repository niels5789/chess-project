package com.example.ChessProject.Model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import sun.util.resources.Bundles;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "ChessGame")
@EntityListeners(AuditingEntityListener.class)
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank
    String playerName;

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Game(int id, @NotBlank String playerName) {
        this.id = id;
        this.playerName = playerName;
    }
}
