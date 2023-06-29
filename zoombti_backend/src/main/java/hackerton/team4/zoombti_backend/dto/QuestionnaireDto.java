package hackerton.team4.zoombti_backend.dto;

import lombok.*;

public class QuestionnaireDto {
    @Data
    @Builder
    @NoArgsConstructor @AllArgsConstructor
    @Getter
    public static class Response {
        private Integer questionIdx;
        private String question;
        private String answer1;
        private String answer2;
        private Integer ordering;
    }
}
