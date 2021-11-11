package com.wanted_server.Service;

import com.wanted_server.Class.*;
import com.wanted_server.Dto.ProfilePersonalDto;
import com.wanted_server.Dto.ProfileTeamPersonalsDto;
import com.wanted_server.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {

    private final PersonalRepository personalRepository;
    private final PersonalTeamRepository personalTeamRepository;
    private final TeamRepository teamRepository;
    private final PostingRepository postingRepository;
    private final ConnectRepository connectRepository;

    // 프로필에서 팀 조회
    public List<ProfileTeamPersonalsDto> getTeams(@PathVariable Long personalId) {
        // 나와 일치하는 팀
        Personal personal = personalRepository.findOne(personalId);
        List<PersonalTeam> personalTeams = personal.getPersonalTeams();

        // 전달해줄 List
        List<ProfileTeamPersonalsDto> teamDto = new ArrayList<>();

        // 전체 PersonalTeam
        List<PersonalTeam> allPersonalTeams = personalTeamRepository.findAll();
        for (PersonalTeam personalTeam : personalTeams) { // 내거 안에서 찾는다.
            Long teamId = personalTeam.getTeam().getId();

            ProfileTeamPersonalsDto profileTeamPersonalsDto = new ProfileTeamPersonalsDto();
            profileTeamPersonalsDto.setTeamId(teamId);
            profileTeamPersonalsDto.setTeamName(personalTeam.getTeam().getTeamName());

            ArrayList<ProfilePersonalDto> personals = new ArrayList<>();
            for (PersonalTeam allPersonalTeam : allPersonalTeams) { // 전체안에서
                if (allPersonalTeam.getTeam().getId() == teamId && allPersonalTeam.getPersonal().getId() != personalId) {
                    Personal tempPersonal = allPersonalTeam.getPersonal();
                    ProfilePersonalDto profilePersonalDto = new ProfilePersonalDto(
                            tempPersonal.getId(), tempPersonal.getNickname(), tempPersonal.getImg(),
                            tempPersonal.getSchool(), tempPersonal.getMajor(), tempPersonal.getAddress()
                    );
                    personals.add(profilePersonalDto);
                }
            }
            profileTeamPersonalsDto.setPersonals(personals);
            teamDto.add(profileTeamPersonalsDto);
        }

        return teamDto;
    }

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
    public Long deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
        return teamId;
    }
}
