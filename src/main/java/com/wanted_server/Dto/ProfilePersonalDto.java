package com.wanted_server.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfilePersonalDto {
    private Long id;
    private String nickname;
    private String img;
    private String school;
    private String major;
    private String address;

}
