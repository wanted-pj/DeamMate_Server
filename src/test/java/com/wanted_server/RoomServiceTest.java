package com.wanted_server;

import com.wanted_server.Class.*;
import com.wanted_server.Dto.PersonalJoinDto;
import com.wanted_server.Dto.PostingCreateDto;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Repository.PostingRepository;
import com.wanted_server.Service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class RoomServiceTest {

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    PersonalService personalService;

    @Autowired
    PostingRepository postingRepository;

    @Autowired
    RoomService roomService;

//    @Test
    @Transactional
//    @Rollback(value = false)
    public void 룸생성() throws Exception {
        // given
        // 회원 2명 생성
        PersonalJoinDto personalJoinDto1 = new PersonalJoinDto("tempId1", "1234",
                "dfas", "adasdsa", "경기대", "경영", 4, 25, 1, "경기도", "개발자");
        PersonalJoinDto personalJoinDto2 = new PersonalJoinDto("tempId2", "12345",
                "nick", "add", "경기대", "컴공", 3, 24, 0, "경기도", "개발자");
        Personal personal1 = new Personal(personalJoinDto1);
        Personal personal2 = new Personal(personalJoinDto2);

        Long senderId = personalService.join(personal1);
        Long receiverId = personalService.join(personal2);

        // when
        // 회원 한명이 채팅 시작을 위해 "쪽지하기"를 누름. => 방생성
        Room room = roomService.makeRoom(senderId, receiverId);

        // 각각의 회원의 room넘버가 같은지 확인
        Personal find = personalService.findOne(senderId);
        boolean check = false;
        for (Participant participant : find.getParticipants()) {
            if (participant.getRoom().getId() == room.getId()) {
                check = true;
            }
        }

        // then
        assertTrue(check);
    }
}
