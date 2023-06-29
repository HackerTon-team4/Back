package hackerton.team4.zoombti_backend.dto;

import lombok.*;

public class MemberDto {
    @Data
    @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        private String email;
        private String password;
        private String name;

    }

    @Data
    @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private String email;
        private String name;
    }
}
