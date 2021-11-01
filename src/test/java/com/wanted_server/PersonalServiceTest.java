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
        // given
        Team team = new Team();
        team.setLeader(false);

        PersonalDto personalDto1 = new PersonalDto("ggp03016", "1234",
                "dfas", "adasdsa", "경기대", "경영", 4, 25, 1, "경기도", "개발자");
        PersonalDto personalDto2 = new PersonalDto("ggp03016", "1234",
                "dfas", "adasdsa", "경기대", "경영", 4, 25, 1, "경기도", "개발자");
        PersonalDto personalDto3 = new PersonalDto("ggp03016", "1234",
                "dfas", "adasdsa", "경기대", "경영", 4, 25, 1, "경기도", "개발자");

        Personal personal1 = new Personal(personalDto1);
        Personal personal2 = new Personal(personalDto2);
        Personal personal3 = new Personal(personalDto3);
        personal1.setTeam(team);
        personal2.setTeam(team);
        personal3.setTeam(team);

        personalRepository.save(personal2);
        personalRepository.save(personal3);

        // when
        Personal addedPersonal = personalRepository.save(personal1);
        teamRepository.save(team);

        Personal findPersonal = personalRepository.findById(addedPersonal.getId()).get();

        // then
        System.out.println("아무렇게나");
        for (Personal personal : findPersonal.getTeam().getPersonals()) {
            System.out.println("Id: " + personal.getId());
        }
        assertEquals(addedPersonal, findPersonal);
    }

}