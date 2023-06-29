package hackerton.team4.zoombti_backend.controller;

import hackerton.team4.zoombti_backend.dto.QuestionnaireDto;
import hackerton.team4.zoombti_backend.service.QuestionnaireService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questionnaire")
public class QuestionnaireController {
    private final QuestionnaireService questionnaireService;

    @GetMapping
    public ResponseEntity<?> getQuestionnaires(@RequestParam int startIdx, @RequestParam int endIdx) {
        log.info("설문지 controller - start: {}, end: {}", startIdx, endIdx);
        HashMap<String, Object> response = new HashMap<>();
        List<QuestionnaireDto.Response> questions;
        try {
            questions = questionnaireService.getQuestionnairesBatch(startIdx, endIdx);
        } catch (Exception e) {
            log.error("설문지 controller - error: {}", e.getMessage());
            response.put("success", "false");
            response.put("message", "설문지를 가져오는데 실패했습니다.");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("success", "true");
        response.put("result", questions);

        return ResponseEntity.ok().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("success", "false");
        response.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
