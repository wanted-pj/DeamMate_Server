package com.wanted_server.Controller;

import com.wanted_server.Class.Team;
import com.wanted_server.Dto.PostingUpdateDto;
import com.wanted_server.Service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class TeamController {

    private final TeamService teamService;

    // 팀 전체 조회, 팀을 조회할 일이 없음
//    @GetMapping("/")
//    public List<Team> getTeams() { return teamService}

    // 팀 생성 (
    @PostMapping("/team/{postingId}")
    public Team createTeam(@PathVariable Long postingId) {
        Team team = teamService.makeTeam(postingId);
        return team;
    }

    // 팀 변경(참여)
    @PutMapping("/team/join/{connectId}")
    public Team joinTeam(@PathVariable Long connectId) {
        return teamService.joinTeam(connectId);
    }

    // 팀 변경(강퇴)
//    @PutMapping("/team/out/{outerId}")
//    public Team outTeam(@PathVariable Long outerId, @RequestBody Long leaderId) {
//        return teamService.joinTeam(outerId, leaderId);
//
//    }

    // 팀 삭제

}
