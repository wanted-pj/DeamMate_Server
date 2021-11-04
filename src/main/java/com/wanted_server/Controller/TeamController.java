package com.wanted_server.Controller;

import com.wanted_server.Class.Team;
import com.wanted_server.Service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TeamController {

    private final TeamService teamService;

    // 팀 전체 조회, 팀을 조회할 일이 없음
//    @GetMapping("/")
//    public List<Team> getTeams() { return teamService}

    // 팀 생성 (
    @PostMapping("/team")
    public Team createTeam(@RequestBody Long postingId) {
        Team team = teamService.makeTeam(postingId);
        return team;
    }

    // 팀 변경(참여)

    // 팀 변경(강퇴)

    // 팀 삭제

}
