package com.wanted_server.Service;

import com.wanted_server.Class.Personal;
import com.wanted_server.Class.Posting;
import com.wanted_server.Dto.PostingUpdateDto;
import com.wanted_server.Repository.PersonalRepository;
import com.wanted_server.Repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostingService {

    private final PostingRepository postingRepository;
    private final PersonalRepository personalRepository;

    // 포스팅 만들기
    public Long make(Posting posting, Long personalId){
        Personal personal = personalRepository.findOne(personalId);
        posting.setPersonal(personal);
        Posting save = postingRepository.save(posting);
        return save.getId();
    }

    // 전체 포스팅 데이터 가져오기
    @Transactional(readOnly = true)
    public List<Posting> findPostings() {
        return postingRepository.findAll();
    }

    // 포스트 수정
    public Long update(Long postingId, PostingUpdateDto postingUpdateDto) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(
                () -> new IllegalArgumentException("해당 포스팅 번호가 존재하지 않습니다.")
        );
        posting.update(postingUpdateDto);
        return posting.getId();
    }

    // 포스트 삭제
    public Long delete(Long postingId) {
        postingRepository.deleteById(postingId);
        return postingId;
    }
}
