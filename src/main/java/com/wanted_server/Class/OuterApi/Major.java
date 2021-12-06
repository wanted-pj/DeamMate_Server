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
public class Major {

    @Column(name = "major_id")
    @Id
    Long id;
    String major_name;
}

