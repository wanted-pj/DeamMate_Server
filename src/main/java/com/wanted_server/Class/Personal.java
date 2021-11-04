package com.wanted_server.Class;

import com.wanted_server.Dto.PersonalDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Setter
public class Personal {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String stringId;

    @Column(nullable = false)
    private String pwd;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<PersonalTeam> personalTeams = new ArrayList<>();

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private String school;

    @Column(nullable = false)
    private String major;

    @Column(nullable = false)
    private int grade;

    @Column
    private int age;

    @Column
    private int gender;

    @Column
    private String career;

    @Column
    private String address;

    @OneToMany(mappedBy = "personal")
    private List<Posting> postings = new ArrayList<>();

    public void addPersonalTeam(PersonalTeam personalTeam) {
        personalTeams.add(personalTeam);
        personalTeam.setPersonal(this);
    }

    public Personal(PersonalDto personalDto) {
        this.stringId = personalDto.stringId;
        this.pwd = personalDto.pwd;
        this.nickname = personalDto.nickname;
        this.img = personalDto.img;
        this.school = personalDto.school;
        this.major = personalDto.major;
        this.grade = personalDto.grade;
        this.age = personalDto.age;
        this.gender = personalDto.gender;
        this.career = personalDto.career;
        this.address = personalDto.address;
    }

    public void update(PersonalDto personalDto) {
        this.stringId = personalDto.stringId;
        this.pwd = personalDto.pwd;
        this.nickname = personalDto.nickname;
        this.img = personalDto.img;
        this.school = personalDto.school;
        this.major = personalDto.major;
        this.grade = personalDto.grade;
        this.age = personalDto.age;
        this.gender = personalDto.gender;
        this.career = personalDto.career;
        this.address = personalDto.address;
    }

}