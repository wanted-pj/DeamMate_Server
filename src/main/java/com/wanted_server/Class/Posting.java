package com.wanted_server.Class;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Posting {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "posting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    private Personal personal;

    private String title;

    private String content;

    private LocalDateTime postingTime;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "posting", cascade = CascadeType.ALL)
    private List<Connect> connects = new ArrayList<>();

    @OneToOne(mappedBy = "posting", fetch = FetchType.LAZY)
    private Team team;

    public void setPersonal(Personal personal) {
        this.personal = personal;
        personal.getPostings().add(this);
    }

    public void setPersonalTeamId(Team team) {
        this.team = team;
        team.setPosting(this);
    }
}
