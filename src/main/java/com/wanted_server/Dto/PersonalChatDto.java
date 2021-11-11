package com.wanted_server.Dto;

import com.wanted_server.Class.Participant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PersonalChatDto {

    private Long id;
    private List<ParticipantInPersonalDto> participants = new ArrayList<>();

    @Builder
    public PersonalChatDto(Long id, List<ParticipantInPersonalDto> participants) {
        this.id = id;
        this.participants = participants;
    }
}
