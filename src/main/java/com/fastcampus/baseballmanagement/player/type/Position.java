package com.fastcampus.baseballmanagement.player.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Position {

    firstBaseman("1루수")
    , secondBaseman("2루수")
    , thirdBaseman("3루수")
    , catcher("포수")
    , Pitcher("투수")
    , shortstop("유격수")
    , leftFielder("좌익수")
    , centerFielder("중견수")
    , rightFielder("우익수")

    ;
    private final String name;

    public static Position getPositionByName(String name) {
        return Arrays.stream(Position.values())
                .filter(position -> position.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
