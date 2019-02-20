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


    @Query(value = "SELECT g.id, g.current_board_position, g.is_finished, g.move_count, g.game_history_id FROM game g, player_game pg WHERE pg.player_id = :playerid ORDER BY ID DESC LIMIT 1", nativeQuery = true)
    Game findLastGamePlayer(@Param("playerid") Integer playerid);
}
