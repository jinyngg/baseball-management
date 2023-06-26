package com.fastcampus.baseballmanagement.team.service.impl;

import com.fastcampus.baseballmanagement.team.dto.Team;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamDAO {

    int insert(Team team);

    List<Team> findAll();
}
