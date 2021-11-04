package com.wanted_server.Controller;

import com.wanted_server.Class.Posting;
import com.wanted_server.Dto.PostingCreateDto;
import com.wanted_server.Dto.PostingUpdateDto;
import com.wanted_server.Repository.PostingRepository;
import com.wanted_server.Service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostingController {

    private final PostingService postingService;

    @GetMapping("/") // 전체 포스팅 조회
    public List<Posting> getPostings() {
        return postingService.findPostings();
    }

    @PostMapping("/posting")
    public Posting createPosting(@RequestBody PostingCreateDto postingCreateDto, @RequestBody Long personalId) {
        Posting posting = new Posting(postingCreateDto);
        postingService.make(posting, personalId);
        return posting;
    }

    @PutMapping("/Posting/{postingId}")
    public Long updatePosting(@PathVariable Long postingId, @RequestBody PostingUpdateDto postingUpdateDto) {
        return postingService.update(postingId, postingUpdateDto);
    }

    @DeleteMapping("/Posting/{postingId}")
    public Long deletePosting(@PathVariable Long postingId) {
        postingService.delete(postingId);
        return postingId;
    }
}
