package com.example.ChessProject.repository;

import com.example.ChessProject.Model.Game.GameHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameHistoryRepository extends JpaRepository<GameHistory, Integer> {

    @Query(value = "SELECT * FROM game_history gh WHERE gh.game_id = :gameid ORDER BY id DESC", nativeQuery = true)
    List<GameHistory> findHistoryFromGame(@Param("gameid") Integer gameid);
}
