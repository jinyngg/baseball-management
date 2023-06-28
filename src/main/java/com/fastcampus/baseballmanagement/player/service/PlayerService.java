package com.fastcampus.baseballmanagement.player.service;

import com.fastcampus.baseballmanagement.player.dto.PlayerList;
import com.fastcampus.baseballmanagement.player.dto.PlayerRegistration;

public interface PlayerService {

    // 선수 등록
    PlayerRegistration registerPlayer(int teamId, String name, String position);

    // 팀별 선수 목록 조회
    PlayerList getPlayerListByTeam(int teamId);
}
