DROP TABLE IF EXISTS roles;
CREATE TABLE roles
(
  id                    BIGSERIAL,
  title                 VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

INSERT INTO roles (title) VALUES ('ROLE_USER');

DROP TABLE IF EXISTS clients_roles;
CREATE TABLE clients_roles
(
  client_id               BIGINT NOT NULL,
  role_id               BIGINT NOT NULL,
  PRIMARY KEY (client_id, role_id),
  FOREIGN KEY (client_id) REFERENCES client (client_id),
  FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO clients_roles (client_id, role_id) VALUES (1, 1);

DROP TABLE IF EXISTS password_reset_tokens;
CREATE TABLE password_reset_tokens
(
  id                    BIGSERIAL,
  token                 VARCHAR(50) NOT NULL,
  expiry_date           TIMESTAMP NOT NULL,
  client_id               BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (client_id) REFERENCES client (client_id)
);