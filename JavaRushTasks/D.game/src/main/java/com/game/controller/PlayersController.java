package com.game.controller;

import com.game.dao.PlayerDAO;
import com.game.entity.Player;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/rest")
public class PlayersController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    @ResponseBody
    public String helloPage() {
        return "Тестовая строчечка";
    }
//
//    @RequestMapping("/players")
//    @ResponseBody
//    public String home() {
//        return playerService.listAll().toArray().toString();
//    }

}
