package com.wanted_server.Controller;

import com.wanted_server.Class.Evaluation;
import com.wanted_server.Dto.EvaluateDto;
import com.wanted_server.Service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EvaluationController {

    private final EvaluationService evaluationService;

    // 평가 반영, 아 여기서 evaluatedId는 평가받는 personalId임
    @PutMapping("/evaluation/{evaluatedId}")
    public Evaluation updateEvaluation(@PathVariable Long evaluatedId, @RequestBody EvaluateDto evaluateDto) {
        return evaluationService.makeEvaluation(evaluatedId, evaluateDto);
    }
}
