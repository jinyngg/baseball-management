package com.fastcampus.baseballmanagement.team.service.impl;

import com.fastcampus.baseballmanagement.team.dto.Team;
import com.fastcampus.baseballmanagement.team.dto.TeamRegistration;
import com.fastcampus.baseballmanagement.team.service.TeamService;

import java.util.List;

public class TeamServiceImpl implements TeamService {

    private static TeamDAO teamDAO;

    public TeamServiceImpl() {
        TeamServiceImpl.teamDAO = TeamDAO.getInstance();
    }

    @Override
    public TeamRegistration.Response registerTeam(TeamRegistration.Request request) {
        TeamRegistration.Response response = teamDAO.registerTeam(request);
        return response;
    }

    @Override
    public List<Team> getTeamList() {
        List<Team> teams = teamDAO.getAllTeams();
        return teams;
    }
}
