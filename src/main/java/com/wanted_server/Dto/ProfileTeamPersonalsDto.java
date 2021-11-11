package com.wanted_server.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class ProfileTeamPersonalsDto {
    Long teamId;
    String teamName;
    ArrayList<ProfilePersonalDto> personals;
}
