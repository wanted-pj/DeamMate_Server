package com.wanted_server.Controller;

import com.wanted_server.Class.Connect;
import com.wanted_server.Service.ConnectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ConnectController {
    private final ConnectService connectService;

    // 전체 커넥트 조회 -> 조회할 일이 없음
//    @GetMapping("/connect")
//    public List<Posting> getConnects() {
//        return connectService.findPostings();
//    }

    // 커넥트 생성
    @PostMapping("/connect/{postingId}/{senderId}")
    public Connect createConnect(@PathVariable Long postingId, @PathVariable Long senderId) {
        Connect connect = new Connect();
        connectService.make(connect, postingId, senderId);
        return connect;
    }

    // 커넥트 처리되면 업데이트
    @PutMapping("/connect/{connectId}")
    public void updateConnect(@PathVariable Long connectId) {
        connectService.updateResult(connectId);
    }

    // 커넥트 삭제
    @DeleteMapping("/connect/{connectId}")
    public Long deleteConnect(@PathVariable Long connectId) {
        connectService.delete(connectId);
        return connectId;
    }
}
