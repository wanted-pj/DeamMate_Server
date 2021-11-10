package com.wanted_server.Dto;

import com.wanted_server.Class.PersonalTeam;
import com.wanted_server.Class.Posting;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class NotRoomTeamInfoPersonalDto {

    private Long id;
    private String stringId;
    private String pwd;
    private String nickname;
    private String img;
    private String school;
    private String major;
    private int grade;
    private int age;
    private int gender;
    private String career;
    private String address;
    private List<Posting> postings = new ArrayList<>();

    public NotRoomTeamInfoPersonalDto(Long id, String stringId, String pwd, String nickname, String img, String school, String major, int grade, int age, int gender, String career, String address, List<Posting> postings) {
        this.id = id;
        this.stringId = stringId;
        this.pwd = pwd;
        this.nickname = nickname;
        this.img = img;
        this.school = school;
        this.major = major;
        this.grade = grade;
        this.age = age;
        this.gender = gender;
        this.career = career;
        this.address = address;
        this.postings = postings;
    }
}
