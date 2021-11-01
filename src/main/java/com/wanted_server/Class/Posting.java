package com.wanted_server.Class;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Posting {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "posting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id")
    private Personal personal;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

}
