DROP TABLE IF EXISTS client;
CREATE TABLE client
(
  id                    BIGSERIAL,
  firstName             VARCHAR(100) NOT NULL,
  lastName              VARCHAR(100) NOT NULL,
  login                 VARCHAR(100) NOT NULL,
  password              VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO client (id, firstName, lastName, login, password) VALUES (1, 'Ivan', 'Ivanov', 'admin', 'qwerty' );

DROP TABLE IF EXISTS cards;
CREATE TABLE cards
(
  id                    BIGINT,
  number                VARCHAR(100) NOT NULL UNIQUE,
  cash                  BIGINT,
  client_id             BIGSERIAL NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (client_id) REFERENCES client (id)
);

INSERT INTO cards (id, number, cash) VALUES (1, '5678654345678907', 1000);

