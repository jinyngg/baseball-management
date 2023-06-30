package com.fastcampus.baseballmanagement.team.service;

import com.fastcampus.baseballmanagement.exception.TeamException;
import com.fastcampus.baseballmanagement.team.dto.TeamList;
import com.fastcampus.baseballmanagement.team.dto.TeamRegistration;

public interface TeamService {

    // 팀 등록
    TeamRegistration registerTeam(String name, int stadiumId) throws TeamException;

    // 팀 목록 조회
    TeamList getTeamList() throws TeamException;
}
