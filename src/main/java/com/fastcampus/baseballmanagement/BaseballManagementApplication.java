package com.fastcampus.baseballmanagement;

import com.fastcampus.baseballmanagement.team.service.impl.TeamServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class BaseballManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseballManagementApplication.class, args);

		TeamServiceImpl teamService = new TeamServiceImpl();

		/** 테스트 코드 성공 */
		Scanner scanner = new Scanner(System.in);
		System.out.println("name 및 id 입력");
		String name = scanner.nextLine();
		int id = Integer.parseInt(scanner.nextLine());

		int result = teamService.registerTeam(name, id);
		System.out.println("결과 = " + result);
		/** 테스트 코드 */
	}

}
