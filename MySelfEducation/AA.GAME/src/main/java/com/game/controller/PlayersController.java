
/*
 * // TODO 24.08.2022 Необходимо оптимизировать проверку по id, она есть везде, где передается id. Надо схлопнуть красиво несколько одинаковых кусков кода
 * // TODO 27.08.2022 Необходимо перенести логику на уровень сервиса
 * // TODO 31.08.2022 Исправить логику удаления. Проверить на тестах.
 *
 *
 *
 *
 *
 */

package com.game.controller;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerService;
import com.game.service.PlayerServiceValidation;
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
import java.util.NoSuchElementException;

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

    @GetMapping("/players/{id}")
    public ResponseEntity<Player> PlayersById(@PathVariable String id) {
        try {
            Long l = Long.parseLong(id);
            if (l <= 0) return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<Player>(playerService.get(l), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
        }
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
    /**
     * Создание игрока.
     * Надо было сделать проверку на Long в JSON. Но происходит автоматическая конвертация на уровне Spring, и я
     * "не успеваю" как схватить long, или не знаю, как это сделать. Дополнительно его считывать отдельно не хочется,
     * хотя может это и правильно. Пока оставлю на аровне проверки даты. Дальше посмотрим.
     * Был долгий квест на то, как проверять года. Нехотелось брать код. Прошил магические числа 2000г и 3000г
     * Не очень хорошо. Пусть научат правильно
     */
    public ResponseEntity<Player> PostPlayer(@RequestBody Player player) {
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
        if (player.getBirthday().getTime() < 0) return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
        if (player.getBirthday().getTime() < 946684800000L)
            return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);  // 2000г костыль
        if (player.getBirthday().getTime() > 32503680000000L)
            return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);// 3000г костыль

        Player tmpPlayer = playerService.save(player);
        return new ResponseEntity<Player>(tmpPlayer, HttpStatus.OK);
    }

    @PostMapping(value = "/players/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> PostById(@PathVariable String id, @RequestBody Player newPlayer) {
//        logger.error(newPlayerJson);
        logger.error(newPlayer);
        // TODO 31.08.2022 пока пишу костыль, не совсем ясно, как правильно делать. Я автоматически преобразую json в объект через spring JPA
        // при этом мне надо проверить json на пустоту
        if (newPlayer.getBirthday() != null && newPlayer.getBirthday().getTime() < 0) return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
        if (newPlayer.getExperience() != null && newPlayer.getExperience() < 0) return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
        if (newPlayer.getExperience() != null && newPlayer.getExperience() > 10000000) return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);

        try {
            Long l = Long.parseLong(id);
            if (l <= 0) return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
            Player oldPlayer = playerService.get(l);

            if (newPlayer.getName() != null)   oldPlayer.setName(newPlayer.getName());
            if (newPlayer.getTitle() != null)  oldPlayer.setTitle(newPlayer.getTitle());
            if (newPlayer.getRace() != null)  oldPlayer.setRace(newPlayer.getRace());
            if (newPlayer.getProfession() != null)  oldPlayer.setProfession(newPlayer.getProfession());
            if (newPlayer.getBirthday() != null)  oldPlayer.setBirthday(newPlayer.getBirthday());
            if (newPlayer.getBanned() != null) oldPlayer.setBanned(newPlayer.getBanned());
            if (newPlayer.getExperience() != null) oldPlayer.setExperience(newPlayer.getExperience());

            playerService.save(oldPlayer);
            return new ResponseEntity<Player>(oldPlayer, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
        } catch (NullPointerException e) {
            return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
        }
    }


















    @DeleteMapping(value = "/players/{id}")
    @ResponseBody
    public ResponseEntity<Player> DeleteById(@PathVariable String id) {
        {
            try {
                return playerService.delete(PlayerServiceValidation.getLong(id));
            }
            catch (Exception e) {
                return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    public ResponseEntity<Player> DeleteByIdSave(@PathVariable String id) {
        try {
            return playerService.delete(PlayerServiceValidation.getLong(id));
        }
        catch (Exception e) {
            return new ResponseEntity<Player>(HttpStatus.BAD_REQUEST);
        }
    }

}