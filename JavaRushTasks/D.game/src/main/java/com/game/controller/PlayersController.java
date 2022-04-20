package com.game.controller;

import com.game.dao.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rest")
public class PlayersController {
    private final PlayerDAO playerDAO;

    @Autowired
    public PlayersController(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @GetMapping("/players")
    public String helloPage() {
        return null;
    }

}
