package com.game.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.dao.PlayerDAO;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<Player> helloPage(@RequestParam(required = false) String name,
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
        rootLogger.info("name: " + name);
        rootLogger.info("title: " + title);
        rootLogger.info("race: " + race);
        rootLogger.info("profession: " + profession);
        rootLogger.info("after: " + after);
        rootLogger.info("before: " + before);
        rootLogger.info("banned: " + banned);
        rootLogger.info("minExperience: " + minExperience);
        rootLogger.info("maxExperience: " + maxExperience);
        rootLogger.info("minLevel: " + minLevel);
        rootLogger.info("maxLevel: " + maxLevel);
        rootLogger.info("order: " + order);
        rootLogger.info("pageNumber: " + pageNumber);
        rootLogger.info("pageSize: " + pageSize);
        return playerService.listWithPagination(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel, order, pageNumber, pageSize);
    }
}
