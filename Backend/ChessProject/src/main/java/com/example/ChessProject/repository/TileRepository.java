package com.example.ChessProject.repository;

import com.example.ChessProject.Model.Tile.Tile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TileRepository extends JpaRepository<Tile, Integer> {
}
