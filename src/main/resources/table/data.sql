-- STADIUM
insert into stadium(name, created_at) values ('서울경기장', now());
insert into stadium(name, created_at) values ('울산경기장', now());
insert into stadium(name, created_at) values ('대구경기장', now());

-- TEAM
INSERT INTO team (stadium_id, name, created_at) VALUES (1, 'SSG', NOW());
INSERT INTO team (stadium_id, name, created_at) VALUES (2, 'LG', NOW());
-- INSERT INTO team (stadium_id, name, created_at) VALUES (3, 'NC', NOW());
