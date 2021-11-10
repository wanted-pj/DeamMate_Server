package com.wanted_server.Controller;

import com.wanted_server.Class.Room;
import com.wanted_server.Service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RoomController {

    private final RoomService roomService;

    // 채팅방 생성
    @PostMapping("/room")
    public Room createRoom(@RequestParam Long senderId, @RequestParam Long receiverId){
        Room room = roomService.makeRoom(senderId, receiverId);
        return room;
    }

}
