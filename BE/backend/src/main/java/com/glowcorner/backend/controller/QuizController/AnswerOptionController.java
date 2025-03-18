package com.glowcorner.backend.controller.QuizController;

import com.glowcorner.backend.model.DTO.AnswerOptionDTO;
import com.glowcorner.backend.model.DTO.response.ResponseData;
import com.glowcorner.backend.service.interfaces.AnswerOptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Answer Option Management System", description = "Operations pertaining to answer options in the Answer Option Management System")
@RestController
@RequestMapping("/api/answer-options")
public class AnswerOptionController {

    private final AnswerOptionService answerOptionService;

    public AnswerOptionController(AnswerOptionService answerOptionService) {
        this.answerOptionService = answerOptionService;
    }

    @Operation(summary = "Get all answer options", description = "Retrieve a list of all available answer options")
    @GetMapping
    public ResponseEntity<ResponseData> getAllAnswerOptions() {
        List<AnswerOptionDTO> answerOptions = answerOptionService.getAllAnswerOptions();
        return ResponseEntity.ok(new ResponseData(200, true, "Answer options found", answerOptions, null, null));
    }

    @Operation(summary = "Get an answer option by ID", description = "Retrieve a single answer option using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getAnswerOptionById(@PathVariable String id) {
        AnswerOptionDTO answerOption = answerOptionService.getAnswerOptionById(id);
        if (answerOption == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "Answer option with ID: " + id + " not found", null, null, null));
        }
        return ResponseEntity.ok(new ResponseData(200, true, "Answer option found", answerOption, null, null));
    }

    @Operation(summary = "Create a new answer option", description = "Add a new answer option to the catalog")
    @PostMapping
    public ResponseEntity<ResponseData> createAnswerOption(@RequestBody AnswerOptionDTO answerOptionDTO) {
        AnswerOptionDTO createdAnswerOption = answerOptionService.createAnswerOption(answerOptionDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseData(201, true, "Answer option created", createdAnswerOption, null, null));
    }

    @Operation(summary = "Update an answer option", description = "Update an existing answer option using its ID")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData> updateAnswerOption(@PathVariable String id, @RequestBody AnswerOptionDTO answerOptionDTO) {
        AnswerOptionDTO updatedAnswerOption = answerOptionService.updateAnswerOption(id, answerOptionDTO);
        if (updatedAnswerOption == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "Answer option with ID: " + id + " not found", null, null, null));
        }
        return ResponseEntity.ok(new ResponseData(200, true, "Answer option updated", updatedAnswerOption, null, null));
    }

    @Operation(summary = "Delete an answer option by ID", description = "Remove an answer option from the system using its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswerOption(@PathVariable String id) {
        answerOptionService.deleteAnswerOption(id);
        return ResponseEntity.noContent().build();
    }
}