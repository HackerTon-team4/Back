package hackerton.team4.zoombti_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class QuestionnaireDto {
    @Data
    @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private Integer questionIdx;
        private String question;
        private String answer1;
        private String answer2;
    }
}
