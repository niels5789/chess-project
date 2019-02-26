package com.example.ChessProject.Model.Game;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
@Entity
public class GameHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int countPlace;
    @Column(length = 500)
    String boardPosition;

    String vanColor;
    String van;
    String naar;
    String gameStatus;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    public GameHistory() {
    }

    public GameHistory(int countPlace, String boardPosition, String vanColor, String van, String naar, Game game) {
        this.countPlace = countPlace;
        this.boardPosition = boardPosition;
        this.vanColor = vanColor;
        this.van = van;
        this.naar = naar;
        this.game = game;
        this.gameStatus = "Active";
    }

    public int getId() {
        return id;
    }


    public int getCountPlace() {
        return countPlace;
    }

    public void setCountPlace(int countPlace) {
        this.countPlace = countPlace;
    }

    public String getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(String boardPosition) {
        this.boardPosition = boardPosition;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getVanColor() {
        return vanColor;
    }

    public void setVanColor(String vanColor) {
        this.vanColor = vanColor;
    }

    public String getVan() {
        return van;
    }

    public void setVan(String van) {
        this.van = van;
    }

    public String getNaar() {
        return naar;
    }

    public void setNaar(String naar) {
        this.naar = naar;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }
}
