package com.wanted_server.Dto;
import com.wanted_server.Class.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostingCreateDto {

    private String title;
    private String content;
    private Category category;
    private String teamName;
    private LocalDateTime postingTime;

    public PostingCreateDto(String title, String content, Category category, String teamName, LocalDateTime postingTime) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.teamName = teamName;
        this.postingTime = postingTime;
    }
}
