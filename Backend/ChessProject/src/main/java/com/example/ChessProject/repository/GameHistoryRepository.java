package com.example.ChessProject.repository;

import com.example.ChessProject.Model.Game.Game_History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameHistoryRepository extends JpaRepository<Game_History, Integer> {
}
