package com.game.service;

import com.game.entity.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

/**
 * Описание класса
 */
public class PlayerServiceValidation {
    /**
     * Возвращает положительный лонг
     * @param stringValue на входе строка
     * @return Long > 0
     * @exception NumberFormatException Если Long <= 0
     */
    public static Long getLong(String stringValue){
        Long returnLong;
            returnLong = Long.parseLong(stringValue);
            if (returnLong <= 0) throw new NumberFormatException();
            return returnLong;
    }
}
