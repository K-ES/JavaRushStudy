package com.game.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.dao.PlayerDAO;
import com.game.entity.Player;
import com.game.service.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Controller
@RequestMapping("/rest")
public class PlayersController {
    static final Logger rootLogger = LogManager.getRootLogger();

    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    @ResponseBody
    public List<Player> helloPage() {
        return  playerService.listAll();
    }
}
