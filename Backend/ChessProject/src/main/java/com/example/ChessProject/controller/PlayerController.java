package com.example.ChessProject.controller;


import com.example.ChessProject.Model.Player.Player;
import com.example.ChessProject.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @ResponseBody
    @GetMapping("/getallplayers")
    public List<Player> listPlayers () {
        return playerRepository.findAll();
    }

//    @ResponseBody
//    @GetMapping("/m")

    @ResponseBody
    @PostMapping("/addplayer")
    public Player addPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    @ResponseBody
    @PutMapping("/checkplayer/{username}/{password}")
    public Player checkPlayer(@PathVariable("username") String username,
                              @PathVariable("password") String password) {
        Player player = new Player(username, password);
        if (playerRepository.findByUsername(username) == null) {
            playerRepository.save(player);
            return player;

        } else {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

               }
        return player = new Player();
    }
}
