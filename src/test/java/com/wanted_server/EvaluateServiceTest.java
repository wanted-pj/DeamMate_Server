package com.wanted_server;

import com.wanted_server.Class.Personal;
import com.wanted_server.Dto.PersonalJoinDto;
import com.wanted_server.Service.PersonalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@Transactional
@SpringBootTest
public class EvaluateServiceTest {

    @Autowired
    PersonalService personalService;

//    @Test
//    @Rollback(value = false)
    public void 회원생성_평가초기화_테스트() throws Exception {
        // 회원 2명 생성
        PersonalJoinDto personalJoinDto1 = new PersonalJoinDto("hz", "hz",
                "싀즨픵", "profile_basic1", "경기대", "경영", 4, 25, 1, "성남시", "개발자를 이끈다 후후후");
        Personal personal1 = new Personal(personalJoinDto1);

        Long leaderId = personalService.join(personal1);

        Personal one = personalService.findOne(leaderId);
        System.out.println(one.getEvaluation());
    }
}
