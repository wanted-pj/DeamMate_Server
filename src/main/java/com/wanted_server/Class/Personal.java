package com.wanted_server.Class;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.wanted_server.Dto.PersonalJoinDto;
import com.wanted_server.Dto.PersonalUpdateDto;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Personal {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String stringId;

    @Column(nullable = false)
    private String pwd;

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

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL)
    private List<Participant> participants = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "evaluation_id")
    @JsonIgnore
    private Evaluation evaluation;

    public void addParticipant(Participant participant) {
        participants.add(participant);
        participant.setPersonal(this);
    }

    public Personal(PersonalJoinDto personalJoinDto) {
        this.stringId = personalJoinDto.stringId;
        this.pwd = personalJoinDto.pwd;
        this.nickname = personalJoinDto.nickname;
        this.img = personalJoinDto.img;
        this.school = personalJoinDto.school;
        this.major = personalJoinDto.major;
        this.grade = personalJoinDto.grade;
        this.age = personalJoinDto.age;
        this.gender = personalJoinDto.gender;
        this.career = personalJoinDto.career;
        this.address = personalJoinDto.address;

    }

    public void update(PersonalUpdateDto personalUpdateDto) {
        this.stringId = personalUpdateDto.stringId;
        this.pwd = personalUpdateDto.pwd;
        this.nickname = personalUpdateDto.nickname;
        this.img = personalUpdateDto.img;
        this.school = personalUpdateDto.school;
        this.major = personalUpdateDto.major;
        this.grade = personalUpdateDto.grade;
        this.age = personalUpdateDto.age;
        this.gender = personalUpdateDto.gender;
        this.career = personalUpdateDto.career;
        this.address = personalUpdateDto.address;
    }

    public void setEvaluation(Evaluation evaluation) {
        evaluation.setPersonal(this);
        this.evaluation = evaluation;
    }
}