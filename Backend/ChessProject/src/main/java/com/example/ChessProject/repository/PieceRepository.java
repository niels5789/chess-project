package com.example.ChessProject.repository;

import com.example.ChessProject.Model.piece.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PieceRepository extends JpaRepository<Piece, Integer> {
}
