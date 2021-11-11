package com.wanted_server.Dto;

import com.wanted_server.Class.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostingUpdateDto {
    private String title;
    private String content;
    private Category category;
    private String teamName;
    private boolean checkRecruiting;
    private LocalDateTime endTime;

    public PostingUpdateDto(String title, String content, Category category, String teamName, boolean checkRecruiting, LocalDateTime endTime) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.teamName = teamName;
        this.checkRecruiting = checkRecruiting;
        this.endTime = endTime;
    }
}
