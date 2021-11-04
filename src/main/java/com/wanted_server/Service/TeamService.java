package com.wanted_server.Service;

import com.wanted_server.Class.*;
import com.wanted_server.Repository.ConnectRepository;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Repository.PostingRepository;
import com.wanted_server.Repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final PersonalRepository personalRepository;
    private final TeamRepository teamRepository;
    private final PostingRepository postingRepository;
    private final ConnectRepository connectRepository;

    // 팀생성
    public Team makeTeam(Long leaderId, Long postingId) {
        Personal leader = personalRepository.findOne(leaderId);
        Posting posting = postingRepository.findById(postingId).get();

        // Personal에서, PersonalTeam에서 처리
        PersonalTeam personalTeam = PersonalTeam.createPersonalTeam(leader);

        // PersonalTeam을 바탕으로 팀생성
        Team team = Team.createTeam(personalTeam, posting);
        return team;
    }

    // 팀 조인
    public Team joinTeam(Long senderId, Long connectId) {
        Personal sender = personalRepository.findOne(senderId);
        Connect connect = connectRepository.findById(connectId).get();
        Team team = connect.getPosting().getTeam();

        // Personal에서, PersonalTeam에서 처리
        PersonalTeam personalTeam = PersonalTeam.createPersonalTeam(sender);

        // 팀 조인
        return team.joinTeam(personalTeam);
    }
}
