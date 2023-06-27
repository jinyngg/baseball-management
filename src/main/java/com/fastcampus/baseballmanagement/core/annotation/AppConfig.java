package com.fastcampus.baseballmanagement.core.annotation;

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

}
