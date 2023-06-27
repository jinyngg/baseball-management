package com.fastcampus.baseballmanagement.core.annotation;

import com.fastcampus.baseballmanagement.team.service.TeamService;
import com.fastcampus.baseballmanagement.team.service.impl.TeamServiceImpl;

@MyConfiguration
public class AppConfig {

    @MyBean
    public TeamService teamService() {
        return new TeamServiceImpl();
    }

}
