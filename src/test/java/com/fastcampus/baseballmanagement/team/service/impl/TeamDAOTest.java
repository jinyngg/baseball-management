package com.fastcampus.baseballmanagement.team.service.impl;

import com.fastcampus.baseballmanagement.team.dto.Team;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
class TeamDAOTest {

    @Autowired
    TeamDAO teamDAO;

    @Test
    public void findAll_test() {
        List<Team> all = teamDAO.findAll();

        System.out.println(all);
    }
}