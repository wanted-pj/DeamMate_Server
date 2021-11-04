package com.wanted_server;

import com.wanted_server.Class.Personal;
import com.wanted_server.Dto.PersonalJoinDto;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Service.PersonalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Transactional
@SpringBootTest
class PersonalServiceTest {

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    PersonalService personalService;

//    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        // given
        PersonalJoinDto personalJoinDto = new PersonalJoinDto("ggp03016", "1234",
                "dfas", "adasdsa", "경기대", "경영", 4, 25, 1, "경기도", "개발자");
        Personal personal1 = new Personal(personalJoinDto);

        // when
        Long savedId = personalService.join(personal1);

        // result
        assertEquals(personal1, personalRepository.findOne(savedId));
    }

//    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        PersonalJoinDto personalJoinDto = new PersonalJoinDto("ggp03016", "1234",
                "dfas", "adasdsa", "경기대", "경영", 4, 25, 1, "경기도", "개발자");
        Personal personal1 = new Personal(personalJoinDto);
        Personal personal2 = new Personal(personalJoinDto);

        // when
        personalService.join(personal1);
        try {
            personalService.join(personal2);
        } catch (IllegalStateException e) {
            return;
        }

        // result
        System.out.println("여기");
        fail("예외가 발생해야한다.");
    }


}