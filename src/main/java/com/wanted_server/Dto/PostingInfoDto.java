package com.wanted_server.Dto;

import com.wanted_server.Class.Category;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class PostingInfoDto {
    private String title;
    private String content;
    private Category category;
    private LocalDateTime postingTime;
    private Long personalId;
}
