package com.wanted_server.Class;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalTeam {

    @Id
    @GeneratedValue
    @Column(name = "personal_team_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id")
    @JsonBackReference
    private Personal personal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    //== 생성 메서드 ==//
    static public PersonalTeam createPersonalTeam(Personal personal) {
        PersonalTeam personalTeam = new PersonalTeam();
        personal.addPersonalTeam(personalTeam);
        System.out.println("CreatePersonal Team여기: " + personal.getId());
        return personalTeam;
    }


}
