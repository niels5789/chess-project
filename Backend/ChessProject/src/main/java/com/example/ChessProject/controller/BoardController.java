package com.example.ChessProject.controller;

import com.example.ChessProject.Model.Board.Board;
import com.example.ChessProject.Model.Board.Tile;
import com.example.ChessProject.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BoardController {

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/boards")
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }


    @PostMapping("/boards")
    public Board createBoard(@Valid @RequestBody Board board)  {
        return boardRepository.save(board);
    }

    @PostMapping("/newboard")
    public Board createNewBoard()  {
        Board board = new Board();

        for (int i=1; i < 9; i++){
            for (int j=1; j < 9; j++) {
                board.getBoardPosition().add(new Tile(i+j-2, null, j, i));
            }
        }

        return boardRepository.save(board);
    }

    // ik weet niet of dit goed is
    @GetMapping("/newboard")
    public List<Board> getAllNewBoards() {
        return boardRepository.findAll();
    }
}
