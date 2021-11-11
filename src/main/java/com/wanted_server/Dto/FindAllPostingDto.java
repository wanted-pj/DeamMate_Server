package com.wanted_server.Dto;

import com.wanted_server.Class.Category;
import com.wanted_server.Class.Connect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FindAllPostingDto {
    private Long postingId;
    private Long personalId;
    private String title;
    private String content;
    private LocalDateTime postingTime;
    private LocalDateTime endTime;
    private Category category;
    private List<Connect> connects;
    private String teamName;
    private Long teamId;
    private String nickname;
    private String img;
    private boolean checkRecruiting;

    public FindAllPostingDto(Long postingId, Long personalId, String title, String content, LocalDateTime postingTime, LocalDateTime endTime, Category category, List<Connect> connects, String teamName, Long teamId, String nickname, String img, boolean checkRecruiting) {
        this.postingId = postingId;
        this.personalId = personalId;
        this.title = title;
        this.content = content;
        this.postingTime = postingTime;
        this.endTime = endTime;
        this.category = category;
        this.connects = connects;
        this.teamName = teamName;
        this.teamId = teamId;
        this.nickname = nickname;
        this.img = img;
        this.checkRecruiting = checkRecruiting;
    }
}
