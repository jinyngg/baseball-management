package com.fastcampus.baseballmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생하였습니다.")
    , INVALID_REQUEST("잘못된 요청입니다.")
    , SQL_EXCEPTION("SQL_EXCEPTION 발생했습니다.")

    , INVALID_POSITION("유효하지 않은 포지션입니다.")
    , DUPLICATE_PLAYER_POSITION("이미 동일한 포지션의 선수가 등록되어 있습니다.")

    , INVALID_PLAYER("등록되어 있는 선수가 없습니다.")
    , INVALID_TEAM("등록되어 있는 팀이 없거나 팀 등록이 안되어 있는 선수입니다.")

    ;

    private final String description;
}
