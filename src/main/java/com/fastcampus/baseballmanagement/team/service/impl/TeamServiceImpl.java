package com.fastcampus.baseballmanagement.team.service.impl;

import com.fastcampus.baseballmanagement.team.dto.Team;
import com.fastcampus.baseballmanagement.team.service.TeamService;

import java.util.List;

public class TeamServiceImpl implements TeamService {

    private static TeamDAO teamDAO;

    public TeamServiceImpl() {
        TeamServiceImpl.teamDAO = TeamDAO.getInstance();
    }

    @Override
    public int registerTeam(String name, int stadiumId) {
        teamDAO.registerTeam(name, stadiumId);
        return stadiumId;
    }

    @Override
    public List<Team> getTeamList() {
        return null;
    }
}
