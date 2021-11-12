package com.wanted_server.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wanted_server.Class.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostingCreateDto {

    private String title;
    private String content;
    private Category category;
    @JsonProperty("team_name")
    private String teamName;
    @JsonProperty("end_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endTime;

    public PostingCreateDto(String title, String content, Category category, String teamName, LocalDateTime endTime) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.teamName = teamName;
        this.endTime = endTime;
    }
}
