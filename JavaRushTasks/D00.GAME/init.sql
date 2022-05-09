CREATE DATABASE IF NOT EXISTS rpg
    COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS test
    COLLATE utf8mb4_unicode_ci;

USE rpg;

DROP TABLE IF EXISTS player;

CREATE TABLE player
(
    id             BIGINT(20)  NOT NULL AUTO_INCREMENT,
    name           VARCHAR(12) NULL,
    title          VARCHAR(30) NULL,
    race           VARCHAR(20) NULL,
    profession     VARCHAR(20) NULL,
    banned         BIT(1)      NULL,
    experience     INT(10)     NULL,
    level          INT(3)      NULL,
    untilNextLevel INT(10)     NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

INSERT INTO player(name, title, race, profession, birthday, banned, experience, level, untilNextLevel)
VALUES ('Ниус', 'Приходящий Без Шума', 'HOBBIT', 'ROGUE', false, 58347, 33, 1153)
;
