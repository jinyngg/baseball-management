package com.fastcampus.baseballmanagement.stadium.service;

import com.fastcampus.baseballmanagement.stadium.dto.Stadium;

import java.util.List;

public interface StadiumService {
    void insertStadium(Stadium stadium);
    List<Stadium> selectStadium();
}
