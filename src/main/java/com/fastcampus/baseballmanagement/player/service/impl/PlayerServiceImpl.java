package com.fastcampus.baseballmanagement.player.service.impl;

import com.fastcampus.baseballmanagement.core.annotation.MyRequestMapping;
import com.fastcampus.baseballmanagement.player.dto.Player;
import com.fastcampus.baseballmanagement.player.dto.PlayerRegistration;
import com.fastcampus.baseballmanagement.player.service.PlayerService;

import java.util.List;

public class PlayerServiceImpl implements PlayerService {

    private static PlayerDAO playerDAO;

    public PlayerServiceImpl() {
        PlayerServiceImpl.playerDAO = PlayerDAO.getInstance();
    }

    @Override
    @MyRequestMapping("선수등록")
    public PlayerRegistration registerPlayer(int teamId, String name, String position) {
        PlayerRegistration response = playerDAO.registerPlayer(teamId, name, position);
        System.out.println(response);
        return response;
    }

    @Override
    public List<Player> getPlayerListByTeam(int teamId) {
        return null;
    }
}
