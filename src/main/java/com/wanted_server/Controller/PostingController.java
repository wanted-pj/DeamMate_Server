package com.wanted_server.Controller;

import com.wanted_server.Class.Posting;
import com.wanted_server.Dto.PostingCreateDto;
import com.wanted_server.Dto.PostingInfoDto;
import com.wanted_server.Dto.PostingUpdateDto;
import com.wanted_server.Repository.PostingRepository;
import com.wanted_server.Service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostingController {

    private final PostingService postingService;
    private final PostingRepository postingRepository;

    // 전체 포스팅 조회
//    @GetMapping("/posting")
//    public List<PostingInfoDto> getPostings() {
//        List<Posting> postings = postingService.findPostings();
//        ArrayList<PostingInfoDto> postingInfoDtos = new ArrayList<>();
//        for (Posting posting : postings) {
//            postingInfoDtos.add(new PostingInfoDto(
//                    posting.getTitle(),
//                    posting.getContent(),
//                    posting.getCategory(),
//                    posting.getPostingTime(),
//                    posting.getPersonal().getId()));
//        }
//        return (List<PostingInfoDto>) postingInfoDtos;
//    }

    @GetMapping("/posting")
    public List<Posting> getPostings() {
        return postingService.findPostings();
    }

    // 사람 Id를 전달받아서 posting 만들기
    @PostMapping("/posting/{personalId}")
    public Posting createPosting(@RequestBody PostingCreateDto postingCreateDto, @PathVariable Long personalId) {
        Posting posting = new Posting(postingCreateDto);
        postingService.make(posting, personalId);
        return posting;
    }

    // 포스트 Id를 전달받아서 posting 수정하기
    @PutMapping("/posting/{postingId}")
    public Long updatePosting(@PathVariable Long postingId, @RequestBody PostingUpdateDto postingUpdateDto) {
        return postingService.update(postingId, postingUpdateDto);
    }

    // 포스팅아이디를 전달받아서 포스팅 삭제하기
    @DeleteMapping("/posting/{postingId}")
    public Long deletePosting(@PathVariable Long postingId) {
        postingService.delete(postingId);
        return postingId;
    }
}
