package com.wanted_server.Dto;

import com.wanted_server.Class.Category;
import com.wanted_server.Class.Connect;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersonalInPostingDto {
    private Long postingId;
    private String title;
    private String content;
    private LocalDateTime postingTime;
    private Category category;
    private List<Connect> connects;
    private String teamName;

    public PersonalInPostingDto(Long postingId, String title, String content, LocalDateTime postingTime, Category category, List<Connect> connects, String teamName) {
        this.postingId = postingId;
        this.title = title;
        this.content = content;
        this.postingTime = postingTime;
        this.category = category;
        this.connects = connects;
        this.teamName = teamName;
    }
}
