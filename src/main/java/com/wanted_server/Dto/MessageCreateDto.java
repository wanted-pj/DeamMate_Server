package com.wanted_server.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageCreateDto {
    public Long roomId;
    public Long senderId;
    public String content;

    public MessageCreateDto(Long roomId, Long senderId, String content) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.content = content;
    }
}
