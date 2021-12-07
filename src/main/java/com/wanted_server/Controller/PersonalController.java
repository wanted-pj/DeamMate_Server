package com.wanted_server.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wanted_server.Class.Personal;
import com.wanted_server.Dto.NotRoomTeamInfoPersonalDto;
import com.wanted_server.Dto.PersonalChatDto;
import com.wanted_server.Dto.PersonalJoinDto;
import com.wanted_server.Dto.PersonalUpdateDto;
import com.wanted_server.Repository.EvaluationRepository;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Service.EvaluationService;
import com.wanted_server.Service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PersonalController {
    private final PersonalRepository personalRepository;
    private final PersonalService personalService;
    private final EvaluationService evaluationService;
    private final EvaluationRepository evaluationRepository;

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

    // stringId로 로그인 정보 조회
    @GetMapping("/personal/login/{stringId}")
    public Personal getLoginPersonal(@PathVariable String stringId) {
        return personalService.findByStringId(stringId);
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

    // nickname 으로 아이디 중복 검사 조회
    @GetMapping("/personal/nickname/{nickname}")
    public void getPersonalsByNickname(@PathVariable String nickname) {
        personalService.validateNicknameDuplicateException(nickname);
    }

    // 회원 생성
    @PostMapping("/personal")
    public Personal createPersonal(@RequestBody PersonalJoinDto personalJoinDto) {
        // 둘다 DB에 저장
        Personal personal = new Personal(personalJoinDto);
        personalService.join(personal);

        return personal;
    }

    // 회원 수정
    @PutMapping("/personal/{personalId}")
    public Map updatePersonal(@PathVariable Long personalId, @RequestBody PersonalUpdateDto personalUpdateDto) {
        Long updateId = personalService.update(personalId, personalUpdateDto);
        Map map = new HashMap();
        map.put("updateId", updateId);
        return map;
    }

    // 회원 삭제
    @DeleteMapping("/personal/{personalId}")
    public Long deletePersonal(@PathVariable Long personalId) {
        personalService.deleteById(personalId);
        return personalId;
    }
}
