package com.wanted_server.Controller;

import com.wanted_server.Class.Personal;
import com.wanted_server.Dto.PersonalJoinDto;
import com.wanted_server.Dto.PersonalUpdateDto;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PersonalController {
    private final PersonalRepository personalRepository;
    private final PersonalService personalService;

    // 모든 회원 조회
    @GetMapping("/personal")
    public List<Personal> getPersonals() {
        return personalRepository.findAll();
    }


    // 학교로 여러개 조회
    @GetMapping("/personal/school")
    public List<Personal> getPersonalsBySchool(@RequestBody Map<String,String> map) {
        return personalRepository.findBySchool(map.get("school"));
    }

    // 전공으로 여러개 조회
    @GetMapping("/personal/major")
    public List<Personal> getPersonalsByMajor(@RequestBody Map<String,String> map) {
        return personalRepository.findByMajor(map.get("major"));
    }

    // 주소로 여러개 조회
    @GetMapping("/personal/address")
    public List<Personal> getPersonalsByAddress(@RequestBody Map<String,String> map) {
        return personalRepository.findByAddress(map.get("address"));
    }

    // stringId로 아이디 중복 검사 조회
    @GetMapping("/personal/stringId")
    public void getPersonalsByStringId(@RequestBody Map<String,String> map) {
        personalService.validateDuplicateException(map.get("stringId"));
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
