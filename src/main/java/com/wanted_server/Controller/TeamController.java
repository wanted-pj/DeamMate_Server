package com.wanted_server.Controller;

import com.wanted_server.Class.Personal;
import com.wanted_server.Class.PersonalTeam;
import com.wanted_server.Class.Team;
import com.wanted_server.Dto.PostingUpdateDto;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TeamController {

    private final TeamService teamService;
    private final PersonalRepository personalRepository;

    // 팀 전체 조회, 팀을 조회할 일이 없음
    @GetMapping("/team/{personalId}")
    public List<PersonalTeam> getTeams(@PathVariable Long personalId) {
        Personal personal = personalRepository.findOne(personalId);
        return personal.getPersonalTeams();
    }

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
