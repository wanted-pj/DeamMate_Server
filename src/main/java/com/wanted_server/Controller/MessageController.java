package com.wanted_server.Controller;

import com.wanted_server.Class.Message;
import com.wanted_server.Dto.MessageCreateDto;
import com.wanted_server.Service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MessageController {

    private final MessageService messageService;

    // 메세지 생성
    @PostMapping("/message")
    public Message createMessage(@RequestBody MessageCreateDto messageCreateDto) {
        return messageService.makeMessage(messageCreateDto);
    }

}
