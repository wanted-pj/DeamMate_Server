package com.wanted_server.Class;

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

    @OneToMany(mappedBy = "personal")
    private List<Posting> postings = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Personal{" +
                "id=" + id +
                ", stringId='" + stringId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", img='" + img + '\'' +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", grade=" + grade +
                ", age=" + age +
                ", gender=" + gender +
                ", career='" + career + '\'' +
                ", address='" + address + '\'' +
                ", postings=" + postings +
                '}';
    }

    public void update(PersonalDto personalDto) {
    }

}