package com.wanted_server.Service;

import com.wanted_server.Class.Personal;
import com.wanted_server.Dto.PersonalDto;
import com.wanted_server.Repository.PersonalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonalService {

    private final PersonalRepository personalRepository;

    /*
    회원가입
     */
    @Transactional
    public Long join(Personal personal){
        validateDuplicateException(personal);
        personalRepository.save(personal);
        return personal.getId();
    }

    private void validateDuplicateException(Personal personal) {
        List<Personal> findPersonal = personalRepository.findByStringId(personal.getStringId());
        if(!findPersonal.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Personal> findPersonals(){
        return  personalRepository.findAll();
    }

    // 단건 조회
    public Personal findOne(Long id){
        return personalRepository.findOne(id);
    }

    // 회원정보 변경
    @Transactional
    public Long update(Long personalId, PersonalDto personalDto) {
        Personal personal = personalRepository.findOne(personalId);
        personal.update(personalDto);
        return personal.getId();
    }

    // 회원정보 삭제
    public void deleteById(Long personalId) {
        personalRepository.delete(personalId);
    }

}

