package com.example.ChessProject.Model.Player;

import com.example.ChessProject.Model.Game.Game;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PlayerGame")
@IdClass(PlayerGameID.class)
public class PlayerGame implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "PLAYER_ID", referencedColumnName = "id")
    private Player player;

    @Id
    @ManyToOne
    @JoinColumn(name = "GAME_ID", referencedColumnName = "id")
    private Game game;

    @Column(name = "has_won_by")
    private boolean hasWonBy;

    public PlayerGame() {
    }

    public PlayerGame(Player player, Game game) {
        this.player = player;
        this.game = game;
    }
}
