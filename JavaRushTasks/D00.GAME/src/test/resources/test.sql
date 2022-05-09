DROP TABLE IF EXISTS player;

CREATE TABLE player
(
    id             BIGINT(20)  NOT NULL AUTO_INCREMENT,
    name           VARCHAR(12) NULL,
    title          VARCHAR(30) NULL,
    race           VARCHAR(20) NULL,
    PRIMARY KEY (id)
);
--     race           VARCHAR(20) NULL,
--     profession     VARCHAR(20) NULL,

INSERT INTO player(name, title, race)
VALUES ('Ниус', 'Приходящий Без Шума', 'HOBBIT')
     , ('Никрашш', 'НайтВульф', 'ORC')
     , ('Эззэссэль', 'шипящая', 'DWARF');