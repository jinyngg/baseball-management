package com.fastcampus.baseballmanagement.player.service;

import com.fastcampus.baseballmanagement.player.dto.Player;
import com.fastcampus.baseballmanagement.player.dto.PlayerRegistration;

import java.util.List;

public interface PlayerService {

    // 선수 등록
    PlayerRegistration registerPlayer(int teamId, String name, String position);

    // 팀별 선수 목록 조회
    List<Player> getPlayerListByTeam(int teamId);
}
