package com.wanted_server.Class;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.wanted_server.Repository.EvaluationRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Evaluation {

    @Id
    @Column(name = "evaluation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    double earnest; // 성실
    double teamwork; // 팀워크
    double contribution; // 기여도
    int count; // 평가 받은 횟수

    @OneToOne(mappedBy = "evaluation", fetch = FetchType.LAZY)
    @JsonIgnore
    private Personal personal;

    public static Evaluation createEvaluation() {
        Evaluation evaluation = new Evaluation();
        evaluation.setEarnest(0);
        evaluation.setTeamwork(0);
        evaluation.setContribution(0);
        evaluation.setCount(0);

        return evaluation;
    }
}
