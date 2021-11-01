package com.wanted_server.Class;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class PersonalDto {
    private Long mem_num;
    private String id;
    private String pwd;
    private String nickname;
}
