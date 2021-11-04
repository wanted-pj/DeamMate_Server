package com.wanted_server;

import com.wanted_server.Class.Personal;
import com.wanted_server.Dto.PersonalJoinDto;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Service.PersonalService;
import com.wanted_server.Service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class TeamServiceTest {

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    PersonalService personalService;

    @Autowired
    TeamService teamService;

    @Test
    @Rollback(value = false)
    public void 팀생성() throws Exception {
        // given
        // 회원 2명 생성
        PersonalJoinDto personalJoinDto = new PersonalJoinDto("ggp03016", "1234",
                "dfas", "adasdsa", "경기대", "경영", 4, 25, 1, "경기도", "개발자");
        Personal personal1 = new Personal(personalJoinDto);

        PersonalJoinDto personalJoinDto2 = new PersonalJoinDto("ggp03016", "1234",
                "dfas", "adasdsa", "경기대", "경영", 4, 25, 1, "경기도", "개발자");
        Personal personal2 = new Personal(personalJoinDto2);
        Long savedId1 = personalService.join(personal1);
        Long savedId2 = personalService.join(personal2);

        // 회원 한명이 Connect를 보냄


        // when


        // result
    }
}
