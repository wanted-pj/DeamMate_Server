package com.wanted_server.Service;

import com.wanted_server.Class.Evaluation;
import com.wanted_server.Class.Personal;
import com.wanted_server.Dto.EvaluateDto;
import com.wanted_server.Repository.EvaluationRepository;
import com.wanted_server.Repository.PersonalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final PersonalRepository personalRepository;

    // 처음에 사람이 생성되고, Evaluation 값 생성 및 평가를 위한 매핑관계 설정
    public Evaluation initEvaluation() {
        Evaluation evaluation = Evaluation.createEvaluation();
        evaluationRepository.save(evaluation);

        return evaluation;
    }

    // 이후의 평가, 평가 받는 사람의 아이디를 받아옴
    public Evaluation makeEvaluation(Long evaluatedId, EvaluateDto evaluateDto) {
        Personal personal = personalRepository.findOne(evaluatedId);
        Evaluation now = personal.getEvaluation();

        // 평균 갱신
        now.setEarnest((now.getEarnest() * now.getCount() + evaluateDto.getEarnest()) / (now.getCount() + 1));
        now.setTeamwork((now.getTeamwork() * now.getCount() + evaluateDto.getTeamwork()) / (now.getCount() + 1));
        now.setContribution((now.getContribution() * now.getCount() + evaluateDto.getContribution()) / (now.getCount() + 1));

        // 개수 갱신
        now.setCount(now.getCount() + 1);
        return evaluationRepository.save(now);
    }
}
