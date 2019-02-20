package com.example.ChessProject.repository;

import com.example.ChessProject.Model.Player.PlayerGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayergameRepository extends JpaRepository<PlayerGame, Integer> {
}
