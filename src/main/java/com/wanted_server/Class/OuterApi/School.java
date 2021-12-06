package com.wanted_server.Class.OuterApi;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class School {

    @Column(name = "school_id")
    @Id
    Long id;
    String school_name;
}

