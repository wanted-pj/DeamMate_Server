package com.wanted_server.Service;

import com.wanted_server.Class.*;
import com.wanted_server.Repository.ConnectRepository;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Repository.PostingRepository;
import com.wanted_server.Repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {

    private final PersonalRepository personalRepository;
    private final TeamRepository teamRepository;
    private final PostingRepository postingRepository;
    private final ConnectRepository connectRepository;

    // 팀생성
    public Team makeTeam(Long postingId) {
        Posting posting = postingRepository.findById(postingId).get();
        // 팀생성 -> 리더 아이디 지정 및 포스팅과 팀의 매핑설정
        Team team = Team.createTeam(posting);

        // Personal에서, PersonalTeam에서 처리
        PersonalTeam personalTeam = PersonalTeam.createPersonalTeam(posting.getPersonal());
        personalTeam.setTeam(team);
        personalRepository.save(posting.getPersonal());

        teamRepository.save(team);

        return team;
    }

    // 팀 조인
    public Team joinTeam(Long connectId) {
        Connect connect = connectRepository.findById(connectId).get();
        Personal sender = personalRepository.findOne(connect.getSenderId());
        Team team = connect.getPosting().getTeam();

        // Personal에서, PersonalTeam에서 처리
        PersonalTeam personalTeam = PersonalTeam.createPersonalTeam(sender);
        personalTeam.setTeam(team); // 팀 조인

        personalRepository.save(sender);
        teamRepository.save(team);

        return team;
    }

    // 팀 탈퇴

    // 팀 삭제

}
