package com.wanted_server.Class;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Team {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "team_id")
    private Long id;

    @Column(nullable = false)
    private Long leaderId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posting_id")
    @JsonIgnore
    private Posting posting;

    private String teamName;

    static public Team createTeam(Posting posting) {
        // 팀 생성
        Team team = new Team();
        team.setLeaderId(posting.getPersonal().getId()); // 리더Id 지정
        team.setTeamName(posting.getTeamName());
        // 팀생성시 posting과 매핑
        team.setPosting(posting);

        return team;
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
        posting.setTeam(this);
    }
}
