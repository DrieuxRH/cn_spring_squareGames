CREATE TABLE players(
                      uuid VARCHAR(36) DEFAULT (UUID()) PRIMARY KEY ,
                      username VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      mail VARCHAR(255),
                      first_name VARCHAR(255),
                      last_name VARCHAR(255)
);