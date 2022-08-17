package com.game.controller;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
@RequestMapping("/rest")
public class PlayersController {
    private static final Logger logger = LogManager.getRootLogger();
    @Autowired
    WebMvcConfigurationSupport mvcConfigurationSupport;



    @Autowired
    private PlayerService playerService;



    @GetMapping("/players")
    @ResponseBody
    public List<Player> PlayersList(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) String title,
                                    @RequestParam(required = false) Race race,
                                    @RequestParam(required = false) Profession profession,
                                    @RequestParam(required = false) Long after,
                                    @RequestParam(required = false) Long before,
                                    @RequestParam(required = false) Boolean banned,
                                    @RequestParam(required = false) Integer minExperience,
                                    @RequestParam(required = false) Integer maxExperience,
                                    @RequestParam(required = false) Integer minLevel,
                                    @RequestParam(required = false) Integer maxLevel,
                                    @RequestParam(required = false) PlayerOrder order,
                                    @RequestParam(defaultValue = "0") int pageNumber,
                                    @RequestParam(defaultValue = "3") int pageSize) {
        return playerService.listWithPagination(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel, order, pageNumber, pageSize).getContent();
    }

    @GetMapping("/players/count")
    @ResponseBody
    public Long PlayersCount(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String title,
                                @RequestParam(required = false) Race race,
                                @RequestParam(required = false) Profession profession,
                                @RequestParam(required = false) Long after,
                                @RequestParam(required = false) Long before,
                                @RequestParam(required = false) Boolean banned,
                                @RequestParam(required = false) Integer minExperience,
                                @RequestParam(required = false) Integer maxExperience,
                                @RequestParam(required = false) Integer minLevel,
                                @RequestParam(required = false) Integer maxLevel,
                                @RequestParam(required = false) PlayerOrder order) {
        return playerService.playersCount(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel);
    }

    @PostMapping(value = "/players", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Player> PostPlayer(@RequestBody Player player)
    {
        if (
                player.getName() == null ||
                        player.getTitle() == null ||
                        player.getRace() == null ||
                        player.getProfession() == null ||
                        player.getBirthday() == null ||
                        player.getExperience() == null
        ) return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);

        if (player.getName().length() > 12) return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
        if (player.getTitle().length() > 30) return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
        if (player.getExperience() < 0) return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
        if (player.getExperience() > 10000000) return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
        // TODO Проверить на отрицательность json birthday long

        Player tmpPlayer = playerService.save(player);
        return new ResponseEntity<Player>(tmpPlayer, HttpStatus.OK);
    }


}
