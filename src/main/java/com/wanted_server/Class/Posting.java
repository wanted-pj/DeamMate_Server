package com.wanted_server.Class;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.wanted_server.Dto.PostingCreateDto;
import com.wanted_server.Dto.PostingUpdateDto;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
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

    public Posting(PostingCreateDto postingCreateDto){
        this.title = postingCreateDto.getTitle();
        this.content = postingCreateDto.getContent();
        this.postingTime = postingCreateDto.getPostingTime();
        this.category = postingCreateDto.getCategory();
    }

    public void update(PostingUpdateDto postingUpdateDto) {
        this.title = postingUpdateDto.getTitle();
        this.content = postingUpdateDto.getContent();
        this.postingTime = postingUpdateDto.getPostingTime();
        this.category = postingUpdateDto.getCategory();
    }

    // 포스팅을 만들 때, 사람과 관계 설정
    public void setPersonal(Personal personal) {
        this.personal = personal;
        personal.getPostings().add(this);
    }

    // 팀생성할 때 포스팅과 팀 관계 설정
    public void setTeam(Team team) {
        this.team = team;
        team.setPosting(this);
    }
}
