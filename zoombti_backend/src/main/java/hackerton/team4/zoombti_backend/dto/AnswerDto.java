package hackerton.team4.zoombti_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

public class AnswerDto {
    @Data
    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        private Integer questionIdx;
        private Integer answer;
    }
}
