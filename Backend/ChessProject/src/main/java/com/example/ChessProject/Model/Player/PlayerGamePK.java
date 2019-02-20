package com.example.ChessProject.Model.Player;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PlayerGamePK {
    @Embeddable
    public class EmployerDeliveryAgentPK implements Serializable {

        @Column(name = "PLAYER_ID")
        private Long player_id;

        @Column(name = "GAME_ID")
        private Long game_id;
    }
}
