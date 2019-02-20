package com.example.ChessProject.controller;


import com.example.ChessProject.Model.Player.Player;
import com.example.ChessProject.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @ResponseBody
    @GetMapping("/getallplayers")
    public List<Player> listPlayers () {
        return playerRepository.findAll();
    }
    @ResponseBody
    @PostMapping("/changeplayername")
    public Player addPlayerName (@RequestBody Player player) {
        Player play = playerRepository.findByUsername(player.getUsername());
        play.setPlayerName(player.getPlayerName());
        playerRepository.save(play);
        System.out.println(play.getUsername() + " " + play.getPlayerName());
        return play;
    }
    @ResponseBody
    @PostMapping("/authenticate")
    public Player authenticatePlayer(@RequestBody Player player) {
        String playernaam = player.getUsername();
        String playerwachtwoord = player.getPassword();
        if (playerRepository.findByUsernameAndPassword(playernaam, playerwachtwoord) != null) {
            System.out.println(playernaam + " is inglogd met het volgende wachtwoord " + playerwachtwoord );
            return playerRepository.findByUsernameAndPassword(playernaam, playerwachtwoord);
        } else {
            return new Player();
        }
    }
    @ResponseBody
    @GetMapping("/getplayer/{username}")
    public Player getPlayer (@RequestBody @Param("username") String username) {
        return playerRepository.findByUsername(username);
    }


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
