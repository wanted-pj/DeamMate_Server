package com.wanted_server;

import com.wanted_server.Class.Message;
import com.wanted_server.Class.Participant;
import com.wanted_server.Class.Personal;
import com.wanted_server.Class.Room;
import com.wanted_server.Dto.MessageCreateDto;
import com.wanted_server.Dto.PersonalJoinDto;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Repository.PostingRepository;
import com.wanted_server.Service.MessageService;
import com.wanted_server.Service.PersonalService;
import com.wanted_server.Service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@Transactional
@SpringBootTest
public class MessageServiceTest {

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    PersonalService personalService;

    @Autowired
    PostingRepository postingRepository;

    @Autowired
    RoomService roomService;

    @Autowired
    MessageService messageService;

//    @Test
    @Transactional
//    @Rollback back(value = false)
    public void 메세지_보내기 () throws Exception {
        // given
        // 회원 2명 생성
        PersonalJoinDto personalJoinDto1 = new PersonalJoinDto("tempId1", "1234",
                "dfas", "adasdsa", "경기대", "경영", 4, 25, 1, "경기도", "개발자");
        PersonalJoinDto personalJoinDto2 = new PersonalJoinDto("tempId2", "12345",
                "nick", "add", "경기대", "컴공", 3, 24, 0, "경기도", "개발자");
        Personal personal1 = new Personal(personalJoinDto1);
        Personal personal2 = new Personal(personalJoinDto2);

        Long senderId = personalService.join(personal1);
        System.out.println("senderId = " + senderId);
        Long receiverId = personalService.join(personal2);

        // 회원 한명이 채팅 시작을 위해 "쪽지하기"를 누름. => 방생성
        Room room = roomService.makeRoom(senderId, receiverId);
        System.out.println("room.getId() = " + room.getId());

        // when
        // 한명이 쪽지를 보냄
        MessageCreateDto messageCreateDto1 = new MessageCreateDto(room.getId(), senderId, "안녕 나랑 같이 팀할래?");
        MessageCreateDto messageCreateDto2 = new MessageCreateDto(room.getId(), senderId, "시간 날때 연락줘");
        messageService.makeMessage(messageCreateDto1);
        messageService.makeMessage(messageCreateDto2);

        // then
        // 다른쪽에서 쪽지를 받았는지 확인
        Personal receiver = personalService.findOne(receiverId);
        for (Participant participant : receiver.getParticipants()) {
            if (participant.getRoom().getId() == messageCreateDto1.getRoomId()) {
                for (Message message : participant.getRoom().getMessages()) {
                    System.out.println(message);
                }
            }
        }
    }
}
