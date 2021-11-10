package com.wanted_server;

import com.wanted_server.Class.*;
import com.wanted_server.Dto.PersonalJoinDto;
import com.wanted_server.Dto.PostingCreateDto;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Repository.PostingRepository;
import com.wanted_server.Service.ConnectService;
import com.wanted_server.Service.PersonalService;
import com.wanted_server.Service.PostingService;
import com.wanted_server.Service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class TeamServiceTest {

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    PersonalService personalService;

    @Autowired
    PostingRepository postingRepository;

    @Autowired
    PostingService postingService;

    @Autowired
    ConnectService connectService;

    @Autowired
    TeamService teamService;

//    @Test
    public void 팀조회() throws Exception {
        // given

        // 회원 2명 생성
        PersonalJoinDto personalJoinDto1 = new PersonalJoinDto("tempId1", "1234",
                "dfas", "adasdsa", "경기대", "경영", 4, 25, 1, "경기도", "개발자");
        PersonalJoinDto personalJoinDto2 = new PersonalJoinDto("tempId2", "12345",
                "nick", "add", "경기대", "컴공", 3, 24, 0, "경기도", "개발자");
        Personal personal1 = new Personal(personalJoinDto1);
        Personal personal2 = new Personal(personalJoinDto2);

        Long leaderId = personalService.join(personal1);
        Long senderId = personalService.join(personal2);

        // 회원 한명이 posting 올림
        PostingCreateDto postingCreateDto = new PostingCreateDto("개발자 구해요", "사람구해요", Category.스터디, "이겨내자 팀", LocalDateTime.now());
        Posting posting = new Posting(postingCreateDto);
        Long postingId = postingService.make(posting, leaderId);
        System.out.println("여기: " + posting);
        // 회원 한명이 Connect를 보냄
        Connect connect = new Connect();
        connectService.make(connect, postingId, senderId);

        // 팀 수락
        // - 팀 생성
        teamService.makeTeam(postingId);
        // - 팀 조인
        teamService.joinTeam(connect.getId());

        // when
        Personal personal = personalRepository.findOne(leaderId);
        System.out.println("peronal조회: " + personal.getId());
        List<PersonalTeam> personalTeams = personal.getPersonalTeams();

        for (PersonalTeam personalTeam : personalTeams) {
            System.out.println("조회:" + personalTeam.getId());
        }

        // result
        return;
    }


}
