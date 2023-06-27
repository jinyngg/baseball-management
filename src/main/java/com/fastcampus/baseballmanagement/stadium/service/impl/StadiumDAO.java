package com.fastcampus.baseballmanagement.stadium.service.impl;

import com.fastcampus.baseballmanagement.stadium.dto.Stadium;

import java.util.List;

interface StadiumDAO {
    Stadium findById(int id);
    List<Stadium> findAll();
    int insert(Stadium stadium);
    int delete(int id);
    int update(Stadium stadium);
}
