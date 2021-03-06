DROP TABLE IF EXISTS client;
CREATE TABLE client
(
  client_id             BIGSERIAL,
  firstname             VARCHAR(100) NOT NULL,
  lastname              VARCHAR(100) NOT NULL,
  login                 VARCHAR(100) NOT NULL,
  password              VARCHAR(100) NOT NULL,
  PRIMARY KEY (client_id)
);

INSERT INTO client (client_id, firstname, lastname, login, password) VALUES (1, 'Ivan', 'Ivanov', 'admin', '$2b$10$ucU4bc/Fdiw8Y5LGXK/fmOk6vBlrveMd63K1UYtgI2bCUyNfppOg.' );
INSERT INTO client (client_id, firstname, lastname, login, password) VALUES (2, 'Petr', 'Petrov', 'quest', '1234' );

DROP TABLE IF EXISTS cards;
CREATE TABLE cards
(
  id                    BIGSERIAL,
  number                VARCHAR(100) NOT NULL UNIQUE,
  cash                  BIGINT,
  client_id             BIGSERIAL NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (client_id) REFERENCES client (client_id)
);

INSERT INTO cards (id, number, cash, client_id) VALUES (1, '5678654345678907', 1000, 1);
INSERT INTO cards (id, number, cash, client_id) VALUES (2, '1111222233334444', 2000, 1);
INSERT INTO cards (id, number, cash, client_id) VALUES (3, '6666777788889999', 10000, 2);

