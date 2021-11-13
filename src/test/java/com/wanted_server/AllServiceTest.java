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
                "피터", "profile_basic1", "수원대학교", "경영학과", 4, 25, 1, "경기도 성남시", "개발 1년차입니다.");
        PersonalJoinDto personalJoinDto2 = new PersonalJoinDto("b", "b",
                "샘", "profile_basic2", "경기대학교", "컴퓨터공학과", 3, 24, 0, "경상남도 진주시", "개발 2년차입니다.");
        PersonalJoinDto personalJoinDto3 = new PersonalJoinDto("m", "m",
                "제임스", "profile_basic3", "경기대학교", "컴퓨터공학과", 4, 25, 1, "서울특별시", "디자인 2년차입니다.");
        PersonalJoinDto personalJoinDto4 = new PersonalJoinDto("n", "n",
                "비비", "profile_basic4", "덕성여대학교", "컴퓨터공학과", 3, 24, 0, "서울특별시", "공모전 수상 기록이있습니다.");
        PersonalJoinDto personalJoinDto5 = new PersonalJoinDto("c", "c",
                "비비", "profile_basic5", "홍익대학교", "경영학과", 3, 24, 0, "경기도 수원시", "개발자를 꿈꾸는 뉴비입니다.");
        Personal personal1 = new Personal(personalJoinDto1);
        Personal personal2 = new Personal(personalJoinDto2);
        Personal personal3 = new Personal(personalJoinDto3);
        Personal personal4 = new Personal(personalJoinDto4);
        Personal personal5 = new Personal(personalJoinDto5);

        Long leaderId = personalService.join(personal1);
        Long senderId = personalService.join(personal2);
        personalService.join(personal3);
        personalService.join(personal4);
        personalService.join(personal5);

        // 회원 한명이 posting 올림
        PostingCreateDto postingCreateDto = new PostingCreateDto("[긴급]Wanted 해커톤 프로잭트에 참가하실 개발자를 구합니다 !", "2021.11월 14일마감인 원티드 해커톤 프로잭트에 참가하실 개발자분을 모십니다. 개발을 못해도 성실히 배워서 수행할 수 있는 분을 찾습니다. 많이 많이 지원해주세요 !", Category.공모전, "원티드 해커톤 우승팀", LocalDateTime.now());
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
        MessageCreateDto messageCreateDto1 = new MessageCreateDto(room.getId(), leaderId, "안녕하세요 혹시 팀 아직 모집하시나요?");
        MessageCreateDto messageCreateDto2 = new MessageCreateDto(room.getId(), senderId, "네 아직 모집중에 있습니다 !");
        MessageCreateDto messageCreateDto3 = new MessageCreateDto(room.getId(), leaderId, "저는 개발직군에 들어가고 싶습니다 !");
        MessageCreateDto messageCreateDto4 = new MessageCreateDto(room.getId(), senderId, "넵 한번 팀원과 상의해보겠습니다 ㅎㅎ");
        messageService.makeMessage(messageCreateDto1);
        messageService.makeMessage(messageCreateDto2);
        messageService.makeMessage(messageCreateDto3);
        messageService.makeMessage(messageCreateDto4);
    }
}
