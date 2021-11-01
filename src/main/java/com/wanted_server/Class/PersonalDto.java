package com.wanted_server.Class;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class PersonalDto {
    private String stringId;
    private String pwd;
    private Team team;
    private String nickname;
    private String img;
    private String school;
    private String major;
    private int grade;
    private int age;
    private int gender;
    private String address;
    private String career;
}