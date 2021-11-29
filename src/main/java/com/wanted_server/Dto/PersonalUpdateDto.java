package com.wanted_server.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonalUpdateDto {
    public String img;
    public String school;
    public String major;
    public int grade;
    public int age;
    public int gender;
    public String address;
    public String career;

    public PersonalUpdateDto(String img, String school, String major, int grade, int age, int gender, String address, String career) {
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
