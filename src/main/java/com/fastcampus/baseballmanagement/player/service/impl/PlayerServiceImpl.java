package com.fastcampus.baseballmanagement.player.service.impl;

import com.fastcampus.baseballmanagement.core.annotation.MyRequestMapping;
import com.fastcampus.baseballmanagement.exception.PlayerException;
import com.fastcampus.baseballmanagement.player.dto.PlayerList;
import com.fastcampus.baseballmanagement.player.dto.PlayerRegistration;
import com.fastcampus.baseballmanagement.player.dto.PositionTeamPlayer;
import com.fastcampus.baseballmanagement.player.service.PlayerService;

public class PlayerServiceImpl implements PlayerService {

    private static PlayerDAO playerDAO;

    public PlayerServiceImpl() {
        PlayerServiceImpl.playerDAO = PlayerDAO.getInstance();
    }

    @Override
    @MyRequestMapping("선수등록")
    public PlayerRegistration registerPlayer(int teamId, String name, String position) throws PlayerException {
        PlayerRegistration response = playerDAO.registerPlayer(teamId, name, position);
        System.out.println(response);
        return response;
    }

    @Override
    @MyRequestMapping("선수목록")
    public PlayerList getPlayerListByTeam(int teamId) throws PlayerException {
        PlayerList response = playerDAO.getPlayerListByTeam(teamId);
        System.out.println(response);
        return response;
    }

    @Override
    @MyRequestMapping("포지션별목록")
    public PositionTeamPlayer getPositionTeamPlayers() throws PlayerException {
        PositionTeamPlayer response = playerDAO.getPositionTeamPlayers();
        System.out.println(response);
        return response;
    }
}
