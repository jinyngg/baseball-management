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