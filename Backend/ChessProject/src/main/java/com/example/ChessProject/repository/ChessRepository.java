package com.example.ChessProject.repository;

import com.example.ChessProject.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChessRepository extends JpaRepository<Game, Integer> {
}
