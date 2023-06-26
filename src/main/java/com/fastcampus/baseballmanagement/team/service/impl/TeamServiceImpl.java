package com.fastcampus.baseballmanagement.team.service.impl;

import com.fastcampus.baseballmanagement.team.dto.Team;
import com.fastcampus.baseballmanagement.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamDAO teamDAO;

    @Override
    public void registerTeam(Team team) {
        teamDAO.insert(team);
    }

    @Override
    public List<Team> getTeamList() {
        return teamDAO.findAll();
    }
}
