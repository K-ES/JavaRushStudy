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
    public @ResponseBody List<Player> helloPage() {
        List<Player> listPlayer =  playerService.listAll();

        //писать результат сериализации будем во Writer(StringWriter)
        StringWriter writer = new StringWriter();

        //это объект Jackson, который выполняет сериализацию
        ObjectMapper mapper = new ObjectMapper();

        // сама сериализация: 1-куда, 2-что
        try {
            mapper.writeValue(writer, listPlayer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //преобразовываем все записанное во StringWriter в строку

        rootLogger.info("result helloPate(): " + writer.toString());
        return  listPlayer;
//        String contentAsString = "[{\"id\":1,\"name\":\"Ниус1\",\"title\":\"Приходящий Без Шума\",\"race\":\"HOBBIT\"},{\"id\":2,\"name\":\"Никрашш\",\"title\":\"НайтВульф\",\"race\":\"ORC\"},{\"id\":3,\"name\":\"Эззэссэль\",\"title\":\"шипящая\",\"race\":\"DWARF\"}]";
//        return contentAsString;
    }

}
