package com.wanted_server.Controller;

import com.wanted_server.Class.Personal;
import com.wanted_server.Class.PersonalTeam;
import com.wanted_server.Class.Posting;
import com.wanted_server.Class.Team;
import com.wanted_server.Dto.PostingUpdateDto;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Repository.PostingRepository;
import com.wanted_server.Service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class TeamController {

    private final TeamService teamService;
    private final PersonalRepository personalRepository;
    private final PostingRepository postingRepository;

    // 회원프로필에서 팀 조회
    @GetMapping("/team/{personalId}")
    public List<Long> getTeams(@PathVariable Long personalId) {
        Personal personal = personalRepository.findOne(personalId);
        List<PersonalTeam> personalTeams = personal.getPersonalTeams();

        Set<Long> teamsIds = new HashSet<>();
        for (PersonalTeam personalTeam : personalTeams) {
            teamsIds.add(personalTeam.getTeam().getId());
        }
        return (List<Long>) teamsIds;
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
