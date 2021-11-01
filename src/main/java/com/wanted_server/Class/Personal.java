package com.wanted_server.Class;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Personal {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String string;

    @Column(nullable = false)
    private String pwd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

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

    public Personal(PersonalDto personalDto) {
        this.stringId = personalDto.getStringId();
        this.pwd = personalDto.getPwd();
        this.team = personalDto.getTeam();
        this.nickname = personalDto.getNickname();
        this.img = personalDto.getImg();
        this.school = personalDto.getSchool();
        this.major = personalDto.getMajor();
        this.grade = personalDto.getGrade();
        this.age = personalDto.getAge();
        this.gender = personalDto.getGender();
        this.address = personalDto.getAddress();
        this.career = personalDto.getCareer();
    }
    @OneToMany(mappedBy = "personal")
    private List<Posting> postings = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

}