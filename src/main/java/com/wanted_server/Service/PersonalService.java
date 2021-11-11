package com.wanted_server.Service;

import com.wanted_server.Class.Personal;
import com.wanted_server.Class.Posting;
import com.wanted_server.Dto.NotRoomTeamInfoPersonalDto;
import com.wanted_server.Dto.PersonalInPostingDto;
import com.wanted_server.Dto.PersonalJoinDto;
import com.wanted_server.Dto.PersonalUpdateDto;
import com.wanted_server.Repository.PersonalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Long join(Personal personal) {
        validateDuplicateException(personal.getStringId());
        personalRepository.save(personal);
        return personal.getId();
    }

    // 회원 중복 조회
    public void validateDuplicateException(String stringId) {
        List<Personal> findPersonal = personalRepository.findByStringId(stringId);
        if (!findPersonal.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<NotRoomTeamInfoPersonalDto> findPersonals() {
        List<Personal> personals = personalRepository.findAll();
        List<NotRoomTeamInfoPersonalDto> dtoAll = new ArrayList<>();
        for (Personal personal : personals) {
            List<PersonalInPostingDto> postings = new ArrayList<>();
            for (Posting posting : personal.getPostings()) {
                PersonalInPostingDto personalInPostingDto = new PersonalInPostingDto(
                        posting.getId(), posting.getTitle(), posting.getContent(),
                        posting.getPostingTime(), posting.getEndTime(), posting.getCategory(),
                        posting.getConnects(), posting.getTeamName(), posting.getCheckRecruiting());
                postings.add(personalInPostingDto);
            }
            NotRoomTeamInfoPersonalDto notRoomTeamInfoPersonalDto = new NotRoomTeamInfoPersonalDto(
                    personal.getId(),
                    personal.getStringId(),
                    personal.getPwd(),
                    personal.getNickname(),
                    personal.getImg(),
                    personal.getSchool(),
                    personal.getMajor(),
                    personal.getGrade(),
                    personal.getAge(),
                    personal.getGender(),
                    personal.getCareer(),
                    personal.getAddress(),
                    postings
            );
            System.out.println("여기: " + notRoomTeamInfoPersonalDto.getId());
            dtoAll.add(notRoomTeamInfoPersonalDto);
        }
        return dtoAll;
    }

    // 단건 조회
    public Personal findOne(Long id) {
        return personalRepository.findOne(id);
    }

    // 회원정보 변경
    @Transactional
    public Long update(Long personalId, PersonalUpdateDto personalUpdateDto) {
        Personal personal = personalRepository.findOne(personalId);
        personal.update(personalUpdateDto);
        return personal.getId();
    }

    // 회원정보 삭제
    public void deleteById(Long personalId) {
        personalRepository.delete(personalId);
    }

}

