package com.fastcampus.baseballmanagement;

import com.fastcampus.baseballmanagement.team.service.impl.TeamServiceImpl;

public class BaseballManagementApplication {

	public static void main(String[] args) {
		TeamServiceImpl teamService = new TeamServiceImpl();
//		List<Team> teamList = teamService.getTeamList();
//		System.out.println(teamList);

		/** 팀 등록 테스트
		 *  응답 -> 팀 등록 성공 : 한화이글스 (정상)
		 * */
		teamService.registerTeam("한화이글스", 3);

	}

}
