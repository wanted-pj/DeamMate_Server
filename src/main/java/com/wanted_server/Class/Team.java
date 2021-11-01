package com.wanted_server.Class;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Team {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "team_id")
    private Long id;

    @OneToMany(mappedBy = "team")
    private List<Personal> personals = new ArrayList<>();

    @Column(nullable = false)
    private Boolean leader;

    public Team(TeamDto teamDto) {
        this.personals = teamDto.getPersonals();
        this.leader = teamDto.getLeader();
    }

    public void update(TeamDto teamDto) {
        this.personals = teamDto.getPersonals();
        this.leader = teamDto.getLeader();
    }

}