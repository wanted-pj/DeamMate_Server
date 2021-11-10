package com.wanted_server.Dto;

import com.wanted_server.Class.Category;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostingUpdateDto {
    private String title;
    private String content;
    private Category category;
    private String teamName;

    public PostingUpdateDto(String title, String content, Category category, String teamName) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.teamName = teamName;
    }
}
