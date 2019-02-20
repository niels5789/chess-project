package com.example.ChessProject.repository;

import com.example.ChessProject.Model.Game.Game;
import com.example.ChessProject.Model.Player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Player findByUsername(String username);
    Player findByUsernameAndPassword(String username, String password);

    @Modifying
    @Query(value = "SELECT * FROM player p, game_player gp  WHERE gp.game_id = :game_id", nativeQuery = true)
    Set<Player> findListByUsername(@Param("game_id") int game_id);
}
