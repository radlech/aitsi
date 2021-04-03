DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS game;

CREATE TABLE player (
  id INT AUTO_INCREMENT PRIMARY KEY,
  position VARCHAR(3) NOT NULL,
  name VARCHAR(64) NOT NULL,
  age INT NOT NULL,
  skills INT NOT NULL,
  exp INT NOT NULL
);

CREATE TABLE game (
  id INT AUTO_INCREMENT PRIMARY KEY,
  type VARCHAR(3) NOT NULL,
  date DATE NOT NULL,
  rival VARCHAR(32) NOT NULL,
  scored INT,
  missed INT
);