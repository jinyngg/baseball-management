package com.fastcampus.baseballmanagement.stadium.service.impl;

import com.fastcampus.baseballmanagement.stadium.Stadium;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
interface StadiumDAO {
    Stadium findById(int id);
    List<Stadium> findAll();
    int insert(Stadium stadium);
    int delete(int id);
    int update(Stadium stadium);
}
