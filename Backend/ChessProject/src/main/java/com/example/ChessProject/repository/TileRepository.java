package com.example.ChessProject.repository;

import com.example.ChessProject.Model.Tile.Tile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface TileRepository extends JpaRepository<Tile, Integer> {

    List<Tile> findByColorAndNameNot(int color, String name);

    Tile findByColorAndName(int color, String name);

}
