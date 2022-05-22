package com.game.service;

import java.util.List;

import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return (List<Player>) repo.findAll();
    }

    public Page<Player> listWithPagination(int pageNumber, int pageSize) {
        rootLogger.info("listWithPagination start");
        return (Page<Player>) repo.findAll(PageRequest.of(pageNumber,pageSize));
    }

    public Player get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
