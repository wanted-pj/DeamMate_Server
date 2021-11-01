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

    public PersonalDto(String stringId, String pwd, String nickname, String img, String school, String major, int grade, int age, int gender, String address, String career) {
        this.stringId = stringId;
        this.pwd = pwd;
        this.nickname = nickname;
        this.img = img;
        this.school = school;
        this.major = major;
        this.grade = grade;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.career = career;
    }


}