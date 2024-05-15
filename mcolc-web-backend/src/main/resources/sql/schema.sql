DROP TABLE IF EXISTS t_inventory;
DROP TABLE IF EXISTS t_player_details;
DROP TABLE IF EXISTS t_item;

CREATE TABLE IF NOT EXISTS t_player_details
(
    uuid         VARCHAR(64) NOT NULL,
    name         VARCHAR(32) NOT NULL,
    health       FLOAT DEFAULT 0.0,
    hunger       INT   DEFAULT 0,
    exp_level    INT   DEFAULT 0,
    exp_progress FLOAT DEFAULT 0.0,
    buff         VARCHAR(64) NOT NULL, /* TODO */
    PRIMARY KEY (uuid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS t_item
(
    uuid      VARCHAR(64) NOT NULL,
    i_key     VARCHAR(64) NOT NULL,
    name      VARCHAR(32) NOT NULL,
    damage    INT         NOT NULL,
    maxDamage INT         NOT NULL,
    count     INT         NOT NULL,
    PRIMARY KEY (uuid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS t_inventory
(
    uuid VARCHAR(64)                                 NOT NULL,
    slot ENUM ('main', 'armor', 'hotBar', 'offhand') NOT NULL,
    i    INT                                         NOT NULL,
    item VARCHAR(64)                                 NOT NULL,
    PRIMARY KEY (uuid, slot, i),
    FOREIGN KEY (uuid) REFERENCES t_player_details (uuid),
    FOREIGN KEY (item) REFERENCES t_item (uuid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

