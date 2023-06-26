package com.fastcampus.baseballmanagement.team.controller;

import com.fastcampus.baseballmanagement.team.dto.Team;
import com.fastcampus.baseballmanagement.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    /**
     * 콘솔 입력임으로 수정 필요
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerTeam(
            @RequestParam("stadiumId") int stadiumId,
            @RequestParam("name") String name
    ) {
        Team team = Team.builder()
                .stadiumId(stadiumId)
                .name(name)
                .build();

        teamService.registerTeam(team);

        return ResponseEntity.status(HttpStatus.OK).body("팀 등록 성공");
    }
}