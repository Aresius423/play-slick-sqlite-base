# --- !Ups


CREATE TABLE Test (
    id           INTEGER PRIMARY KEY AUTOINCREMENT,
    name         NVARCHAR(255) NOT NULL
);


# --- !Downs
DROP TABLE IF EXISTS Test;