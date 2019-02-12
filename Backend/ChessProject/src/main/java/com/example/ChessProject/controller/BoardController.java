package com.example.ChessProject.controller;

import com.example.ChessProject.Model.Board.Board;
import com.example.ChessProject.Model.piece.Color;
import com.example.ChessProject.Model.piece.Piece;
import com.example.ChessProject.repository.BoardRepository;
import com.example.ChessProject.repository.PieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BoardController {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    PieceRepository pieceRepository;

    @ResponseBody
    @GetMapping("/boards")
    public List<Board> getAllBoards() {
        return boardRepository.findAll();


    }

//    @ResponseBody
//    @PostMapping("/boards")
//    public Board createBoard(@Valid @RequestBody Board board)  {
//        return boardRepository.save(board);
//    }

    @ResponseBody
    @PutMapping("/board/{idvan}/{idnaar}")
    public List<Piece> changeBoard(@PathVariable(value = "idvan") int idvan, @PathVariable(value = "idnaar") int idnaar) {
        List<Piece> piecelist = pieceRepository.findAll();

        //Omslachtig manier van data veranderen. Kan makkelijker
        String newName = piecelist.get(idvan).getName();
        int newColor = piecelist.get(idnaar).getColor();
        piecelist.get(idvan).setName("");
        piecelist.get(idvan).setColor(3);
        piecelist.get(idnaar).setName(newName);
        piecelist.get(idnaar).setColor(newColor);
        for(Piece piece : piecelist) {
            pieceRepository.save(piece);
        }
        return pieceRepository.findAll();
    }
    @ResponseBody
    @GetMapping("/resetBoard")
    public List<Piece> resetBoard() {
        Piece p = new Piece();
        List<Piece> piecelist = p.startList();
        for (Piece piece : piecelist) {
            pieceRepository.save(piece);
        }
        return pieceRepository.findAll();
}

    @ResponseBody
    @GetMapping("/newboard")
    public List<Piece> updateBoard() {
        if (!pieceRepository.existsById(1)) {
        Piece p = new Piece();
        List<Piece> piecelist = p.startList();
        for (Piece piece : piecelist) {
            pieceRepository.save(piece);
        }}

        List<Piece> aanpassing = pieceRepository.findAll();

        aanpassing.get(1).setName("Paard");
        for(Piece piece : aanpassing ) {
            pieceRepository.save(piece);
        }
        return pieceRepository.findAll();
    }
////
//    @ResponseBody
//    @GetMapping("/newboard")
//    public List<Board> getAllNewBoards() {
//        return boardRepository.findAll();
//        }
}
