package com.glowcorner.backend.controller.QuizController;

import com.glowcorner.backend.entity.mongoDB.AnswerOption;
import com.glowcorner.backend.model.DTO.response.ResponseData;
import com.glowcorner.backend.service.interfaces.AnswerOptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/answer-options")
public class AnswerOptionController {

    private final AnswerOptionService answerOptionService;

    public AnswerOptionController(AnswerOptionService answerOptionService) {
        this.answerOptionService = answerOptionService;
    }

    @GetMapping("/question/{questionID}")
    public ResponseEntity<ResponseData> getAnswerOptionsByQuestionID(@PathVariable String questionID) {
        try {
            List<AnswerOption> answerOptions = answerOptionService.getAnswerOptionByQuestionID(questionID);
            return ResponseEntity.ok(new ResponseData(200, true, "Answer options retrieved successfully", answerOptions, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseData(500, false, "Failed to retrieve answer options: " + e.getMessage(), null, null, null));
        }
    }
}
