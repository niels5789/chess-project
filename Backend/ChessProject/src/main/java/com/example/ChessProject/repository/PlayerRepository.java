package com.example.ChessProject.repository;

import com.example.ChessProject.Model.Player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Player findByUsername(String username);

}
