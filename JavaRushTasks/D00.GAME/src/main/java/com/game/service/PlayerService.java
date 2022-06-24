package com.game.service;

import java.util.List;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.PlayerRepository;
import com.game.repository.PlayerSpecification;
import com.game.repository.SearchCriteria;
import com.game.repository.SearchOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Transactional
public class PlayerService {
    static final Logger rootLogger = LogManager.getRootLogger();
    @Autowired
    PlayerRepository repo;

    public void save(Player player) {
        repo.save(player);
    }

    public List<Player> listAll() {
        return repo.findAll();
    }

    public Page<Player> listWithPagination(
            String name,
            String title,
            Race race,
            Profession profession,
            Long after,
            Long before,
            Boolean banned,
            Integer minExperience,
            Integer maxExperience,
            Integer minLevel,
            Integer maxLevel,
            PlayerOrder order,
            int pageNumber,
            int pageSize
            ) {
        rootLogger.info("listWithPagination start");
        rootLogger.info("name" + name);
        rootLogger.info("title" + title);
        rootLogger.info("race" + race);
        rootLogger.info("profession" + profession);
        rootLogger.info("after" + after);
        rootLogger.info("before" + before);
        rootLogger.info("banned" + banned);
        rootLogger.info("minExperience" + minExperience);
        rootLogger.info("maxExperience" + maxExperience);
        rootLogger.info("minLevel" + minLevel);
        rootLogger.info("maxLevel" + maxLevel);
        rootLogger.info("order" + order);
        rootLogger.info("pageNumber" + pageNumber);
        rootLogger.info("pageSize" + pageSize);


        PlayerSpecification playerSpecification = new PlayerSpecification();

        if (name != null) {
            playerSpecification.add(new SearchCriteria("name", name, SearchOperation.MATCH));
        }
        if (title != null) {
            playerSpecification.add(new SearchCriteria("title123", title, SearchOperation.MATCH));
        }
        Page<Player> resultList = repo.findAll(playerSpecification, PageRequest.of(pageNumber, pageSize));



        return resultList;
    }

    public Player get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
