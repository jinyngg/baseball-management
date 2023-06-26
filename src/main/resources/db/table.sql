<<<<<<< HEAD
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
=======
create table stadium(
    id int primary key auto_increment,
    name varchar(20) not null,
    created_at TIMESTAMP not null
);
>>>>>>> ebce833735e7d8ad45fe9da69a09d2d665f1bc65
