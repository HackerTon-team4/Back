package hackerton.team4.zoombti_backend.controller;

import hackerton.team4.zoombti_backend.domain.Personality;
import hackerton.team4.zoombti_backend.domain.Stock;
import hackerton.team4.zoombti_backend.domain.Trait;
import hackerton.team4.zoombti_backend.dto.AnswerDto;
import hackerton.team4.zoombti_backend.dto.QuestionnaireDto;
import hackerton.team4.zoombti_backend.dto.TraitDto;
import hackerton.team4.zoombti_backend.service.PersonalityService;
import hackerton.team4.zoombti_backend.service.QuestionnaireService;
import hackerton.team4.zoombti_backend.service.TraitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questionnaire")
public class QuestionnaireController {
    private final QuestionnaireService questionnaireService;
    private final TraitService traitService;
    private final PersonalityService personalityService;

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
    @PostMapping("/submit")
    public ResponseEntity<?> submitQuestionnaire(@RequestBody Map<String, List<AnswerDto.Request>> result) {
        log.info("{}", result);
        List<AnswerDto.Request> answers = result.get("answers");
        log.info("설문지 제출 controller - answers: {}", answers);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("http://35.78.177.182:5000/api/dataframe")
                .build();
        try {
            RestTemplate restTemplate = new RestTemplate();
            TraitDto dto = restTemplate.postForObject(uriComponents.toUri(), answers, TraitDto.class);
            //log.info("{}", dto.getJbtiCode());
            List<Stock> stocks = Objects.requireNonNull(dto).getStocks();
            Trait trait = traitService.getTraitById(Objects.requireNonNull(dto).getJbti());
            Personality personality = personalityService.getPersonalityByMbti(dto.getMbti());

            HashMap<String, Object> response = new HashMap<>();
            response.put("success", "true");
            response.put("trait", trait);
            response.put("personality", personality);
            response.put("stocks", stocks);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            log.error("성향 획득 service - error: {}", e.getMessage());
            throw new RuntimeException("성향 획득에 실패했습니다.");
        }

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("success", "false");
        response.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
