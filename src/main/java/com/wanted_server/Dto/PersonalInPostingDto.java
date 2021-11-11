package com.wanted_server.Dto;

import com.wanted_server.Class.Category;
import com.wanted_server.Class.Connect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PersonalInPostingDto {
    private Long postingId;
    private String title;
    private String content;
    private LocalDateTime postingTime;
    private LocalDateTime endTime;
    private Category category;
    private List<Connect> connects;
    private String teamName;
    private boolean checkRecruiting;

    public PersonalInPostingDto(Long postingId, String title, String content, LocalDateTime postingTime, LocalDateTime endTime, Category category, List<Connect> connects, String teamName, boolean checkRecruiting) {
        this.postingId = postingId;
        this.title = title;
        this.content = content;
        this.postingTime = postingTime;
        this.endTime = endTime;
        this.category = category;
        this.connects = connects;
        this.teamName = teamName;
        this.checkRecruiting = checkRecruiting;
    }
}
