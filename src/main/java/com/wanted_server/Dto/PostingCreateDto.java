package com.wanted_server.Dto;
import com.wanted_server.Class.Category;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class PostingCreateDto {

    private String title;
    private String content;
    private LocalDateTime postingTime;
    private Category category;

    public PostingCreateDto(String title, String content, LocalDateTime postingTime, Category category) {
        this.title = title;
        this.content = content;
        this.postingTime = postingTime;
        this.category = category;
    }
}
