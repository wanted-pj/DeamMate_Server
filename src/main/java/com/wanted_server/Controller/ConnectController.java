package com.wanted_server.Controller;

import com.wanted_server.Class.Posting;
import com.wanted_server.Dto.PostingCreateDto;
import com.wanted_server.Dto.PostingUpdateDto;
import com.wanted_server.Service.ConnectService;
import com.wanted_server.Service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ConnectController {
    private final ConnectService connectService;

    // 전체 커넥트 조회 -> 조회할 일이 없음
//    @GetMapping("/connect")
//    public List<Posting> getConnects() {
//        return connectService.findPostings();
//    }

    @PostMapping("/connect")
    public Posting createPosting(@RequestBody PostingCreateDto postingCreateDto, @RequestBody Long personalId) {
        Posting posting = new Posting(postingCreateDto);
        postingService.make(posting, personalId);
        return posting;
    }

    @PutMapping("/posting/{postingId}")
    public Long updatePosting(@PathVariable Long postingId, @RequestBody PostingUpdateDto postingUpdateDto) {
        return postingService.update(postingId, postingUpdateDto);
    }

    @DeleteMapping("/posting/{postingId}")
    public Long deletePosting(@PathVariable Long postingId) {
        postingService.delete(postingId);
        return postingId;
    }
}
