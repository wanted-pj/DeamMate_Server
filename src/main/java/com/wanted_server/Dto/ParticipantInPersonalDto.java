package com.wanted_server.Dto;

import com.wanted_server.Class.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantInPersonalDto{

    private Room room;
    private String nickname;
    private String img;
    private Long theOtherPersonalId;
    private LocalDateTime lastMessageTime;
}
