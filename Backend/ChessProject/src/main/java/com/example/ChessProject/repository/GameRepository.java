package com.example.ChessProject.repository;

import com.example.ChessProject.Model.Game.Game;
import com.example.ChessProject.Model.Player.Player;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    List<Game> findByPlayer(Player player);
    @Query(value = "SELECT * FROM game g WHERE player_id = :playerid ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Game findLastGamePlayer(@Param("playerid") Integer playerid);

    @Query(value = "SELECT * FROM game g WHERE player_id = :playerid ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Game> findLastFiveGames(@Param("playerid") Integer playerid);


}
