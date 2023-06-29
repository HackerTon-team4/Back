package hackerton.team4.zoombti_backend.repository;

import hackerton.team4.zoombti_backend.domain.Questionnaire;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {
    Slice<Questionnaire> findQuestionnairesByOrderingBetweenOrderByOrderingAsc(int startIdx, int endIdx);
};
