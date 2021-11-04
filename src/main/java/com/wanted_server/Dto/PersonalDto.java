package com.wanted_server.Dto;

import com.wanted_server.Class.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class PersonalDto {
    public String stringId;
    public String pwd;
    public String nickname;
    public String img;
    public String school;
    public String major;
    public int grade;
    public int age;
    public int gender;
    public String address;
    public String career;

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