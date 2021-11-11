package com.wanted_server.Controller;

import com.wanted_server.Class.Personal;
import com.wanted_server.Class.Posting;
import com.wanted_server.Dto.*;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PersonalController {
    private final PersonalRepository personalRepository;
    private final PersonalService personalService;

    // 모든 회원 조회
    @GetMapping("/personal")
    public List<NotRoomTeamInfoPersonalDto> getPersonals() {
        return personalService.findPersonals();
    }

    // id로 회원의 모든 정보 조회
    @GetMapping("/personal/{id}")
    public Personal getPersonals(@PathVariable Long id) {
        return personalService.findOne(id);
    }

    // 채팅ListFragment 들어왔을 때
    @GetMapping("/personal/chat/{id}")
    public PersonalChatDto getChatPersonal(@PathVariable Long id) {
        return personalService.getChatPersonal(id);
    }

    // stringId로 아이디 중복 검사 조회
    @GetMapping("/personal/stringId/{stringId}")
    public void getPersonalsByStringId(@PathVariable String stringId) {
        personalService.validateDuplicateException(stringId);
    }

    // 회원 생성
    @PostMapping("/personal")
    public Personal createPersonal(@RequestBody PersonalJoinDto personalJoinDto) {
        Personal personal = new Personal(personalJoinDto);
        personalService.join(personal);
        return personal;
    }

    // 회원 수정
    @PutMapping("/personal/{personalId}")
    public Long updatePersonal(@PathVariable Long personalId, @RequestBody PersonalUpdateDto personalUpdateDto) {
        return personalService.update(personalId, personalUpdateDto);
    }

    // 회원 삭제
    @DeleteMapping("/personal/{personalId}")
    public Long deletePersonal(@PathVariable Long personalId) {
        personalService.deleteById(personalId);
        return personalId;
    }
}
