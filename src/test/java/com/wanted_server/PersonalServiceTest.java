package com.wanted_server;

import com.wanted_server.Class.Personal;
import com.wanted_server.Class.PersonalDto;
import com.wanted_server.Class.Team;
import com.wanted_server.Class.TeamRepository;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Service.PersonalService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
class PersonalServiceTest {
    @Autowired
    PersonalService personalService;

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        // 개인객체 다 만듬
        PersonalDto personalDto1 = new PersonalDto("ggp03016", "1234",
                "dfas", "adasdsa", "경기대", "경영", 4, 25, 1, "경기도", "개발자");
        PersonalDto personalDto2 = new PersonalDto("ggp03016", "1234",
                "dfas", "adasdsa", "경기대", "경영", 4, 25, 1, "경기도", "개발자");
        PersonalDto personalDto3 = new PersonalDto("ggp03016", "1234",
                "dfas", "adasdsa", "경기대", "경영", 4, 25, 1, "경기도", "개발자");

        Personal personal1 = new Personal(personalDto1);
        Personal personal2 = new Personal(personalDto2);
        Personal personal3 = new Personal(personalDto3);
        Personal addedPersonal1 = personalRepository.save(personal1);
        Personal addedPersonal2 = personalRepository.save(personal2);
        Personal addedPersonal3 = personalRepository.save(personal3);

        // 한 개인이 팀생성 (팀 생성 서비스)
        Personal findPersonal = personalRepository.findById(addedPersonal1.getId()).get();
        Team team = new Team();
        team.setLeaderId(findPersonal.getId());
        findPersonal.setTeam(team);

        // 다른 회원들이 팀 수락을 요청
        Personal findPersonal2 = personalRepository.findById(addedPersonal2.getId()).get();
        Personal findPersonal3 = personalRepository.findById(addedPersonal3.getId()).get();

        findPersonal2.setTeam(team);
        findPersonal3.setTeam(team);

        teamRepository.save(team);

        // when
        // 다시 개인의 팀원을 조회
        Personal reFindPersonal = personalRepository.findById(personal1.getId()).get();

        // then
        System.out.println("아무렇게나");
        for (Personal personal : findPersonal.getTeam().getPersonals()) {
            System.out.println("Id: " + personal);
        }
        assertEquals(personal1, reFindPersonal);
    }

}