package com.fastcampus.baseballmanagement.team.service;

import com.fastcampus.baseballmanagement.team.dto.Team;

import java.util.List;

public interface TeamService {

    // 팀 등록
    void registerTeam(Team team);

    // 팀 목록 조회
    List<Team> getTeamList();
}
