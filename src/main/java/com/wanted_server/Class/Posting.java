package com.wanted_server.Class;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Personal personal;

    private String title;

    private String content;

    private LocalDateTime postingTime;

    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "posting", cascade = CascadeType.ALL)
    private List<Connect> connects = new ArrayList<>();

    @OneToOne(mappedBy = "posting", fetch = FetchType.LAZY)
    @JsonIgnore
    private Team team;

    private String teamName;

    private Boolean checkRecruiting;

    public Posting(PostingCreateDto postingCreateDto){
        this.title = postingCreateDto.getTitle();
        this.content = postingCreateDto.getContent();
        this.category = postingCreateDto.getCategory();
        this.teamName = postingCreateDto.getTeamName();
        this.endTime = postingCreateDto.getEndTime();
        this.checkRecruiting = true;

        // 시간은 나중에 설정
        this.postingTime = LocalDateTime.now();
    }

    public void update(PostingUpdateDto postingUpdateDto) {
        this.title = postingUpdateDto.getTitle();
        this.content = postingUpdateDto.getContent();
        this.category = postingUpdateDto.getCategory();
        this.teamName = postingUpdateDto.getTeamName();
        this.endTime = postingUpdateDto.getEndTime();
        this.checkRecruiting = postingUpdateDto.isCheckRecruiting();

        // 업데이트 시간으로 시간 설정
        this.postingTime = LocalDateTime.now();
    }

    // 포스팅을 만들 때, 사람과 관계 설정
    public void setPersonal(Personal personal) {
        this.personal = personal;
        personal.getPostings().add(this);
    }

    @Override
    public String toString() {
        return "Posting{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", postingTime=" + postingTime +
                ", category=" + category +
                ", connects=" + connects +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
