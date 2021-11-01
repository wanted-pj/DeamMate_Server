package com.wanted_server.Class;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Personal {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "personal_id")
    private Long id;

    @Column(nullable = false)
    private String stringId;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "personal")
    private List<Posting> postings = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

}