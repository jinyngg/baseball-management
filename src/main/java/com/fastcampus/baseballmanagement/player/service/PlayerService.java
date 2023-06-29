package com.fastcampus.baseballmanagement.player.service;

import com.fastcampus.baseballmanagement.player.dto.PlayerList;
import com.fastcampus.baseballmanagement.player.dto.PlayerRegistration;
import com.fastcampus.baseballmanagement.player.dto.PositionTeamPlayer;

public interface PlayerService {

    // 선수 등록
    PlayerRegistration registerPlayer(int teamId, String name, String position);

    // 팀별 선수 목록 조회
    PlayerList getPlayerListByTeam(int teamId);

    // 포지션별 팀 야구 선수 조회
    PositionTeamPlayer getPositionTeamPlayers();
}
