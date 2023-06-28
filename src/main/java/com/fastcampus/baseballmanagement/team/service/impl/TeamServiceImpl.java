package com.fastcampus.baseballmanagement.team.service.impl;

import com.fastcampus.baseballmanagement.core.annotation.MyRequestMapping;
import com.fastcampus.baseballmanagement.team.dto.TeamList;
import com.fastcampus.baseballmanagement.team.dto.TeamRegistration;
import com.fastcampus.baseballmanagement.team.service.TeamService;

public class TeamServiceImpl implements TeamService {

    private static TeamDAO teamDAO;

    public TeamServiceImpl() {
        TeamServiceImpl.teamDAO = TeamDAO.getInstance();
    }

    @Override
    @MyRequestMapping("팀등록")
    public TeamRegistration registerTeam(String name, int stadiumId) {
        TeamRegistration response = teamDAO.registerTeam(name, stadiumId);
        System.out.println(response);
        return response;
    }

    @Override
    @MyRequestMapping("팀목록")
    public TeamList getTeamList() {
        TeamList response = teamDAO.getTeamList();
        System.out.println(response);
        return response;
    }
}
