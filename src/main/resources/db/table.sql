-- STADIUM 생성문

-- TEAM 생성문
CREATE TABLE team (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      stadium_id INT,
                      name VARCHAR(100),
                      created_at TIMESTAMP,
                      FOREIGN KEY (stadium_id) REFERENCES stadiums(id)
);

-- PLAYER 생성문