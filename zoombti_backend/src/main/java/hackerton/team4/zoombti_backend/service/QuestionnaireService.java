package hackerton.team4.zoombti_backend.service;

import hackerton.team4.zoombti_backend.domain.Questionnaire;
import hackerton.team4.zoombti_backend.dto.QuestionnaireDto;
import hackerton.team4.zoombti_backend.repository.QuestionnaireRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;

    // 질문지를 가져오는 메소드
    public List<QuestionnaireDto.Response> getQuestionnairesBatch(int startIdx, int endIdx) {
        List<QuestionnaireDto.Response> questionnaireBatch = new ArrayList<>();
        for(Questionnaire questionnaire : questionnaireRepository.findQuestionnairesByQuestionIdxBetween(startIdx, endIdx)) {
            questionnaireBatch.add(QuestionnaireDto.Response.builder()
                    .questionIdx(questionnaire.getQuestionIdx())
                    .question(questionnaire.getQuestion())
                    .answer1(questionnaire.getAnswer1())
                    .answer2(questionnaire.getAnswer2())
                    .build());
        }
        return questionnaireBatch;
    }
}
