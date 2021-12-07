package com.wanted_server.Service;

import com.wanted_server.Class.*;
import com.wanted_server.Dto.*;
import com.wanted_server.Repository.ParticipantRepository;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Repository.RoomRepository;
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
    private final ParticipantRepository participantRepository;
    private final EvaluationService evaluationService;

    /*
    회원가입
     */
    @Transactional
    public Long join(Personal personal) {
        validateDuplicateException(personal.getStringId());
        validateNicknameDuplicateException(personal.getNickname());
        Evaluation evaluation = evaluationService.initEvaluation();
        personal.setEvaluation(evaluation);
        personalRepository.save(personal);

        return personal.getId();
    }

    // 회원 중복 조회
    public void validateDuplicateException(String stringId) {
        List<Personal> findPersonal = personalRepository.findByStringId(stringId);
        if (!findPersonal.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 ID 입니다.");
        }
    }

    // 회원 중복 조회
    public void validateNicknameDuplicateException(String nickname) {
        List<Personal> findPersonal = personalRepository.findByNickname(nickname);
        if (!findPersonal.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 Nickname 입니다.");
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
            dtoAll.add(notRoomTeamInfoPersonalDto);
        }
        return dtoAll;
    }

    // 단건 조회
    public Personal findOne(Long id) {
        return personalRepository.findOne(id);
    }

    // Id로 조회ㅏ
    public Personal findByStringId(String stringId) {
        List<Personal> findPersonal = personalRepository.findByStringId(stringId);
        if (findPersonal.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 stringId 입니다.");
        }
        return findPersonal.get(0);
    }

    // 채팅 내역 조회
    public PersonalChatDto getChatPersonal(Long id) {
        Personal personal = personalRepository.findOne(id);
        List<Participant> myParticipants = personal.getParticipants();
        List<Participant> allParticipants = participantRepository.findAll();

        List<ParticipantInPersonalDto> ParticipantInPersonalDtos = new ArrayList<>();

        for (Participant myParticipant : myParticipants) {
            Long myRoomId = myParticipant.getRoom().getId();// 나의 룸
            ParticipantInPersonalDto participantInPersonalDto = new ParticipantInPersonalDto();
            participantInPersonalDto.setRoom(myParticipant.getRoom());

            // 상대방 찾기
            for (Participant temp : allParticipants) {
                // 찾았다.
                if (myRoomId == temp.getRoom().getId() && id != temp.getPersonal().getId()) {
                    Personal theOther = personalRepository.findOne(temp.getPersonal().getId());
                    participantInPersonalDto.setTheOtherPersonalId(theOther.getId());
                    participantInPersonalDto.setImg(theOther.getImg());
                    participantInPersonalDto.setNickname(theOther.getNickname());

                    List<Message> messages = temp.getRoom().getMessages();
                    if (messages != null && !messages.isEmpty()) {
                        participantInPersonalDto.setLastMessageTime(messages.get(messages.size() - 1).getMessagingTime());
                    }
                }
            }
            ParticipantInPersonalDtos.add(participantInPersonalDto);
        }

        return PersonalChatDto.builder()
                .participants(ParticipantInPersonalDtos)
                .id(personal.getId())
                .build();
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

