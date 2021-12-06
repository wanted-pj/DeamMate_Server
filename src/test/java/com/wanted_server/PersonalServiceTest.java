package com.wanted_server;

import com.wanted_server.Class.Personal;
import com.wanted_server.Dto.PersonalJoinDto;
import com.wanted_server.Dto.PersonalUpdateDto;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Service.PersonalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class PersonalServiceTest {

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    PersonalService personalService;

    @Test
    @Rollback(value = true)
    public void 회원가입() throws Exception {
        // given
        PersonalJoinDto personalJoinDto = new PersonalJoinDto("ggp03016", "1234",
                "dfas", "adasdsa", "경기대", "경영", 4, 25, 1, "경기도", "개발자");
        Personal personal1 = new Personal(personalJoinDto);

        // when
        Long savedId = personalService.join(personal1);

        // result
        assertEquals(personal1, personalRepository.findOne(savedId)); // JUnit4
//        assertThat(personal1).isEqualTo(personalRepository.findOne(savedId)); // assertJ
    }

    @Test
    public void 회원가입_stringID_중복_예외() throws Exception {
        // given
        PersonalJoinDto personalJoinDto1 = new PersonalJoinDto("firstId", "1234",
                "firstNickname", "profile_basic1", "경기대", "경영", 4, 25, 1, "경기도", "개발자");
        PersonalJoinDto personalJoinDto2 = new PersonalJoinDto("firstId", "1234",
                "secondNickname", "profile_basic2", "경기대", "경영", 4, 25, 1, "경기도", "개발자");
        Personal personal1 = new Personal(personalJoinDto1);
        Personal personal2 = new Personal(personalJoinDto2);

        personalService.join(personal1);

        // result
        assertThatThrownBy(() -> {
            // when
            personalService.join(personal2);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 존재하는 ID 입니다.");
    }

    @Test
    public void 회원가입_nickname_중복_예외() throws Exception {
        // given
        PersonalJoinDto personalJoinDto1 = new PersonalJoinDto("firstId", "1234",
                "firstNickname", "profile_basic1", "경기대학교", "경영학과", 4, 25, 1, "경기도", "개발자");
        PersonalJoinDto personalJoinDto2 = new PersonalJoinDto("secondId", "1234",
                "firstNickname", "profile_basic2", "경기대학교", "경영학과", 4, 25, 1, "경기도", "개발자");
        Personal personal1 = new Personal(personalJoinDto1);
        Personal personal2 = new Personal(personalJoinDto2);

        personalService.join(personal1);

        // result
        assertThatThrownBy(() -> {
            // when
            personalService.join(personal2);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 존재하는 Nickname 입니다.");
    }

    @Test
    public void 회원정보_변경() throws Exception {
        // given
        PersonalJoinDto personalJoinDto1 = new PersonalJoinDto("firstId", "1234",
                "firstNickname", "profile_basic1", "경기대", "경영학과", 4, 25, 1, "경기도 성남시", "개발자");
        Personal personal1 = new Personal(personalJoinDto1);
        personalService.join(personal1);

        // when
        PersonalUpdateDto personalUpdateDto = new PersonalUpdateDto("profile_basic2", "수원대", "컴퓨터공학과", 4, 25, 1, "경기도 수원시", "개발자");
        Long update = personalService.update(personal1.getId(), personalUpdateDto);

        // result
        Personal findOne = personalRepository.findOne(update);
        assertThat(findOne).extracting("major")
                .contains("컴퓨터공학과");
    }
}