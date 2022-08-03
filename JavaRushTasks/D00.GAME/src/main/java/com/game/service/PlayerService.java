package com.game.service;

import java.util.Date;
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

    private PlayerSpecification generateSpecification(String name,
                                 String title,
                                 Race race,
                                 Profession profession,
                                 Long after,
                                 Long before,
                                 Boolean banned,
                                 Integer minExperience,
                                 Integer maxExperience,
                                 Integer minLevel,
                                 Integer maxLevel) {
        PlayerSpecification playerSpecification = new PlayerSpecification();

        if (name != null) {
            playerSpecification.add(new SearchCriteria("name", name, SearchOperation.MATCH));
        }
        if (title != null) {
            playerSpecification.add(new SearchCriteria("title", title, SearchOperation.MATCH));
        }
        if (race != null) {
            playerSpecification.add(new SearchCriteria("race", race, SearchOperation.EQUAL));
        }
        if (profession != null) {
            playerSpecification.add(new SearchCriteria("profession", profession, SearchOperation.EQUAL));
        }
        if (banned != null) {
            playerSpecification.add(new SearchCriteria("banned", banned, SearchOperation.EQUAL));
        }
        if (maxLevel != null) {
            playerSpecification.add(new SearchCriteria("level", maxLevel, SearchOperation.LESS_THAN_EQUAL));
        }
        if (minExperience != null) {
            playerSpecification.add(new SearchCriteria("experience", minExperience, SearchOperation.GREATER_THAN_EQUAL));
        }
        if (maxExperience != null) {
            playerSpecification.add(new SearchCriteria("experience", maxExperience, SearchOperation.LESS_THAN_EQUAL));
        }
        // TO DO скорректировать вызов, чтобы не возникало исключения
        // Корректировка была в PlayerSpecification.java
        if (after != null) {
            playerSpecification.add(new SearchCriteria("birthday", after, SearchOperation.DATE_GREATER_THAN_EQUAL));
        }
        if (before != null) {
            playerSpecification.add(new SearchCriteria("birthday", before, SearchOperation.DATE_LESS_THAN_EQUAL));
        }
        return     playerSpecification;
    }

    public Long playersCount(String name,
                                String title,
                                Race race,
                                Profession profession,
                                Long after,
                                Long before,
                                Boolean banned,
                                Integer minExperience,
                                Integer maxExperience,
                                Integer minLevel,
                                Integer maxLevel) {
        return repo.count(generateSpecification(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel));
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


        Page<Player> resultList = repo.findAll(generateSpecification(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel), PageRequest.of(pageNumber, pageSize));


       return resultList;
    }

    public Player get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
