-- STADIUM 생성문
create table stadium(
                        stadium_id int primary key auto_increment,
                        name varchar(20) not null,
                        created_at TIMESTAMP not null
);

-- TEAM 생성문
CREATE TABLE team (
                      team_id INT AUTO_INCREMENT PRIMARY KEY,
                      stadium_id INT,
                      name VARCHAR(100),
                      created_at TIMESTAMP,
                      FOREIGN KEY (stadium_id) REFERENCES stadium(stadium_id)
);

-- PLAYER 생성문
CREATE TABLE player (
                      player_id INT AUTO_INCREMENT PRIMARY KEY,
                      team_id INT,
                      name VARCHAR(20),
                      position VARCHAR(20),
                      created_at TIMESTAMP,
                      FOREIGN KEY (team_id) REFERENCES team(team_id),
                      UNIQUE KEY unique_team_position (team_id, position)
);

-- OUT_PLAYER 생성문
CREATE TABLE out_player (
                            out_player_id INT AUTO_INCREMENT PRIMARY KEY,
                            player_id INT,
                            reason VARCHAR(20),
                            created_at TIMESTAMP,
                            FOREIGN KEY (player_id) REFERENCES player(player_id)
);