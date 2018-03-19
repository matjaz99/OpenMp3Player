CREATE TABLE mp3file (
  id          INTEGER PRIMARY KEY,
  hash        VARCHAR(64) NOT NULL,
  path        VARCHAR(1024) NOT NULL);