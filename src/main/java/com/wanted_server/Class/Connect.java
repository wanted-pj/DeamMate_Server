package com.wanted_server.Class;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Connect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "connect_id")
    private Long id;

//    private Personal leader;
//
//    private Personal participant;

}
