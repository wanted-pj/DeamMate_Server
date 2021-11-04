package com.wanted_server.Dto;

import com.wanted_server.Class.Category;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostingUpdateDto {
    private String title;
    private String content;
    private LocalDateTime postingTime;
    private Category category;

    public PostingUpdateDto(String title, String content, LocalDateTime postingTime, Category category) {
        this.title = title;
        this.content = content;
        this.postingTime = postingTime;
        this.category = category;
    }
}
