package com.fastcampus.baseballmanagement.team.service;

import com.fastcampus.baseballmanagement.team.dto.TeamList;
import com.fastcampus.baseballmanagement.team.dto.TeamRegistration;

public interface TeamService {

    // 팀 등록
    TeamRegistration registerTeam(String name, int stadiumId);

    // 팀 목록 조회
    public TeamList getTeamList();
}
