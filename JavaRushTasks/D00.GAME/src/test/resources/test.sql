DROP TABLE IF EXISTS player;

CREATE TABLE player
(
    id             BIGINT(20)  NOT NULL AUTO_INCREMENT,
    name           VARCHAR(12) NULL,
    title          VARCHAR(30) NULL,
    PRIMARY KEY (id)
);
--     race           VARCHAR(20) NULL,
--     profession     VARCHAR(20) NULL,

INSERT INTO player(name, title)
VALUES ('Ниус', 'Приходящий Без Шума')
     , ('Никрашш', 'НайтВульф')
     , ('Эззэссэль', 'шипящая');