
## 1️⃣ 요구사항

1. 모든 결과는 Console에 출력

```
입력 : 야구장목록
출력 : [Stadium(stadiumId=1, name=서울경기장, createdAt=2023-06-26 20:55:49.0), Stadium(stadiumId=2, name=울산경기장, createdAt=2023-06-26 20:55:49.0), Stadium(stadiumId=3, name=대구경기장, createdAt=2023-06-26 20:55:49.0)]
```

2. DB 연결 Connection을 생성하는 클래스인 ````DBConnection```` 생성

```java
public class DBConnection {
    public static Connection getInstance() {
        // MySQL 연결 정보
        String url = "jdbc:mysql://localhost:3306/baseball";
        String username = username;
        String password = password;

        // JDBC 드라이버 로드
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("debug : DB has been connected");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

3. 모델은 테이블과 다르게 camel 표기법으로 변수 네이밍

![image](https://github.com/jinyngg/baseball-management/assets/96164211/0907efe3-68bb-4860-b5a8-8789944b3799)

## 2️⃣ DB 

````create.sql````

```sql
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
```

````다이어그램````

![image](https://github.com/jinyngg/baseball-management/assets/96164211/ad92fb42-c0fa-4d3e-a06f-e43fcc39bbce)

## 3️⃣ 구현 기능

### 야구장 등록

````
요청 : 야구장등록?name=잠실야구장
응답 : 성공이라는 메시지를 출력한다.
````

![image](https://github.com/jinyngg/baseball-management/assets/96164211/4c3cad87-8d02-4eaf-911f-c4b26b1c9ca4)

### 전체 야구장 목록보기

````
요청 : 야구장목록
응답 : 야구장 목록은 Model -> Stadium을 List에 담아서 출력한다.
````

![image](https://github.com/jinyngg/baseball-management/assets/96164211/5cf13f39-33ec-4baf-9d69-5726f073206a)

### 팀 등록

````
요청 : 팀등록?stadiumId=1&name=NC
응답 : 성공이라는 메시지를 출력한다.
````

![image](https://github.com/jinyngg/baseball-management/assets/96164211/709c7df3-4de0-42fa-b401-645c69b8e081)

### 전체 팀 목록

````
요청 : 팀목록
응답 : 팀 목록은 Stadium 정보를 조인해서 출력해야 된다. TeamRespDTO가 필요하다.
````

![image](https://github.com/jinyngg/baseball-management/assets/96164211/9e34a9ab-ffa7-4574-b810-b2acdb1d5b67)

### 선수 등록

````
요청 : 선수등록?teamId=1&name=이대호&position=1루수
응답 : 성공이라는 메시지를 출력한다.

포지션은 아래와 같이 중복되지 않고 입력되어야 합니다.(같은 포지션에 두 명의 선수가 등록될 수 없습니다.)
player 테이블에 포지션 칼럼은 팀 별로 유일해야 합니다.(player 테이블의 team_id와 position은 다중 칼럼 유니크 제약조건이 필요)
````

![image](https://github.com/jinyngg/baseball-management/assets/96164211/66c3f885-71b5-45c7-850f-6acbb0a11eba)

### 팀별 선수 목록

````
요청 : 선수목록?teamId=1
응답 : 선수 목록은 Model -> Player를 List에 담아서 출력한다. (team_id는 출력하지 않아도 된다)
````

![image](https://github.com/jinyngg/baseball-management/assets/96164211/eb1c7e68-427c-429c-93da-b98fdd271382)

### 선수 퇴출 등록

````
요청 : 퇴출등록?playerId=1&reason=도박
응답 : 성공이라는 메시지를 출력합니다.

두 개 이상의 write 문이 실행되어야 합니다.(트랜잭션 관리가 Service에서 필요)
out_player에 퇴출 선수를 insert하고, player 테이블에서 해당 선수의 team_id를 null로 변경합니다.
````

````호출 전````

![image](https://github.com/jinyngg/baseball-management/assets/96164211/1774baac-88b3-461b-91e5-7fb155f75f58)

````호출 후````

![image](https://github.com/jinyngg/baseball-management/assets/96164211/a56863b2-1980-4b35-978a-d67fa408f3ad)

### 포지션별 팀 야구 선수 페이지

````
요청 : 포지션별목록
응답 : PositionRespDto 에 값을 담아서 콘솔에 출력합니다.
````

![image](https://github.com/jinyngg/baseball-management/assets/96164211/c4afcde0-16d4-400a-84c6-df9ec0a3ff7d)

## 4️⃣ 응답형식

````
Response =
{message = '포지션별 팀 야구 선수 조회에 성공했습니다.',
data = {3루수={SSG=장진영}, 2루수={SSG=이대호, LG=김원호}, 외야수={1=이대호}, 1루수={JYTEAM=장진영, LG=이대호}}
}
````
