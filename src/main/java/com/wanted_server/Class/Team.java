package com.wanted_server.Class;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Setter
public class Team {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "team_id")
    private Long id;

    @Column(nullable = false)
    private Long leaderId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "posting_id")
    private Posting posting;

    static public Team createTeam(PersonalTeam leaderPersonalTeam, Posting posting) {
        // 팀 생성
        Team team = new Team();
        team.setLeaderId(leaderPersonalTeam.getPersonal().getId()); // 리더Id 지정
        leaderPersonalTeam.setTeam(team);

        // 팀생성시
        posting.setTeam(team);

        return team;
    }

    public Team joinTeam(PersonalTeam senderPersonalTeam){
        senderPersonalTeam.setTeam(this);
        return this;
    }
}
