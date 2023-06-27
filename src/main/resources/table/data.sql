-- STADIUM
insert into stadium(name, created_at) values ('서울경기장', now());
insert into stadium(name, created_at) values ('울산경기장', now());
insert into stadium(name, created_at) values ('대구경기장', now());

-- TEAM
INSERT INTO team (stadium_id, name, created_at) VALUES (1, 'SSG', NOW());
INSERT INTO team (stadium_id, name, created_at) VALUES (2, 'LG', NOW());
-- INSERT INTO team (stadium_id, name, created_at) VALUES (3, 'NC', NOW());

-- PLAYER
INSERT INTO player (team_id, name, position, created_at) VALUES (1, '추신수', '1루수', now());
INSERT INTO player (team_id, name, position, created_at) VALUES (1, '김광현', '2루수', now());

-- 요구사항) 3.8 선수 퇴출 목록
SELECT p.player_id, p.name, p.position, o.reason, o.created_at
FROM player p LEFT OUTER JOIN out_player o
ON p.player_id = o.player_id;