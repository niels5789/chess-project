package com.example.ChessProject.controller;


import com.example.ChessProject.Model.Player.Player;
import com.example.ChessProject.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.List;

@CrossOrigin("localhost:4200")
@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @ResponseBody
    @PostMapping("/addplayer")
    public Player addPlayer(@RequestBody Player player){
        return playerRepository.save(player);
    }

    @ResponseBody
    @GetMapping("/checkplayer/{username}/{password}")
    public Player checkPlayer(@PathVariable ("username") String username, @PathVariable ("password") String password){
        List<Player> playerList = playerRepository.findAll();
        int id = 0;
        for (Player player:playerList){
            if(player.getPlayerName().equals(username)&&player.getPassword().equals(password)){
                id = player.getId();
            }
        }

        return playerList.get(id);
    }


}
