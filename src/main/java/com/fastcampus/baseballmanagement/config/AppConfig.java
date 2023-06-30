package com.fastcampus.baseballmanagement.config;

import com.fastcampus.baseballmanagement.core.annotation.MyBean;
import com.fastcampus.baseballmanagement.core.annotation.MyConfiguration;
import com.fastcampus.baseballmanagement.exception.GlobalExceptionHandler;
import com.fastcampus.baseballmanagement.outPlayer.service.OutPlayerService;
import com.fastcampus.baseballmanagement.outPlayer.service.impl.OutPlayerServiceImpl;
import com.fastcampus.baseballmanagement.player.service.PlayerService;
import com.fastcampus.baseballmanagement.player.service.impl.PlayerServiceImpl;
import com.fastcampus.baseballmanagement.stadium.service.StadiumService;
import com.fastcampus.baseballmanagement.stadium.service.impl.StadiumServiceImpl;
import com.fastcampus.baseballmanagement.team.service.TeamService;
import com.fastcampus.baseballmanagement.team.service.impl.TeamServiceImpl;

@MyConfiguration
public class AppConfig {

    @MyBean
    public StadiumService stadiumService() {
        return new StadiumServiceImpl();
    }

    @MyBean
    public TeamService teamService() {
        return new TeamServiceImpl();
    }

    @MyBean
    public PlayerService playerService() {
        return new PlayerServiceImpl();
    }

    @MyBean
    public OutPlayerService outPlayerService() {
        return new OutPlayerServiceImpl();
    }

    @MyBean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

}
