package com.wanted_server;

import com.wanted_server.Class.*;
import com.wanted_server.Dto.MessageCreateDto;
import com.wanted_server.Dto.PersonalJoinDto;
import com.wanted_server.Dto.PostingCreateDto;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Repository.PostingRepository;
import com.wanted_server.Service.*;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Transactional
@SpringBootTest
public class AllServiceTest {

    @Autowired
    PersonalService personalService;

    @Autowired
    ConnectService connectService;

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    PostingRepository postingRepository;

    @Autowired
    PostingService postingService;

    @Autowired
    TeamService teamService;

    @Autowired
    RoomService roomService;

    @Autowired
    MessageService messageService;

//    @Test
//    @Rollback(value = false)
    public void 전체테스트() throws Exception {
        // 회원 2명 생성
        PersonalJoinDto personalJoinDto1 = new PersonalJoinDto("a", "a",
                "싀즨픵", "profile_basic1", "경기대", "경영", 4, 25, 1, "성남시", "개발자를 이끈다 후후후");
        PersonalJoinDto personalJoinDto2 = new PersonalJoinDto("b", "b",
                "오바마", "profile_basic2", "경기대", "컴공", 3, 24, 0, "진주시", "개발자를 꿈꾸다 후후후");
        PersonalJoinDto personalJoinDto3 = new PersonalJoinDto("m", "m",
                "아베쉑", "profile_basic3", "경기대", "경영", 4, 25, 1, "서울시", "개발자를 이끈다 후후후");
        PersonalJoinDto personalJoinDto4 = new PersonalJoinDto("n", "n",
                "괴물쥐", "profile_basic4", "경기대", "컴공", 3, 24, 0, "진주시", "개발자를 꿈꾸다 후후후");
        Personal personal1 = new Personal(personalJoinDto1);
        Personal personal2 = new Personal(personalJoinDto2);
        Personal personal3 = new Personal(personalJoinDto3);
        Personal personal4 = new Personal(personalJoinDto4);

        Long leaderId = personalService.join(personal1);
        Long senderId = personalService.join(personal2);
        personalService.join(personal3);
        personalService.join(personal4);

        // 회원 한명이 posting 올림
        PostingCreateDto postingCreateDto = new PostingCreateDto("개발자 구해요", "사람구해요", Category.스터디, "이겨내자 팀", LocalDateTime.now());
        Posting posting = new Posting(postingCreateDto);
        Long postingId = postingService.make(posting, leaderId);
        System.out.println("여기: " + posting);

        // 회원 한명이 Connect를 보냄
        Connect connect = new Connect();
        connectService.make(connect, postingId, senderId);

        // 팀 수락
        connectService.updateResult(connect.getId());
        // - 팀 생성
        Team team1 = teamService.makeTeam(postingId);
        System.out.println("여기: " + team1);
        // - 팀 조인
        Team team = teamService.joinTeam(connect.getId());
        System.out.println("여기: " + team);
        // 회원 한명이 채팅 시작을 위해 "쪽지하기"를 누름. => 방생성
        Room room = roomService.makeRoom(leaderId, senderId);
        System.out.println("room.getId() = " + room.getId());

        // when
        // 한명이 쪽지를 보냄
        MessageCreateDto messageCreateDto1 = new MessageCreateDto(room.getId(), leaderId, "안녕 나랑 같이 팀할래?");
        MessageCreateDto messageCreateDto2 = new MessageCreateDto(room.getId(), senderId, "오키 콜 함 가보던가");
        MessageCreateDto messageCreateDto3 = new MessageCreateDto(room.getId(), senderId, "좋아 우린 팀이야 내가 팀장이야");
        MessageCreateDto messageCreateDto4 = new MessageCreateDto(room.getId(), senderId, "내가 부하야");
        messageService.makeMessage(messageCreateDto1);
        messageService.makeMessage(messageCreateDto2);
        messageService.makeMessage(messageCreateDto3);
        messageService.makeMessage(messageCreateDto4);
    }
}
