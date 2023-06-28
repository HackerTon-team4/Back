package hackerton.team4.zoombti_backend.controller;

import hackerton.team4.zoombti_backend.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MainController {
    @GetMapping("/")
    public ResponseEntity<Object> main() {
        ResponseDto responseDto = ResponseDto.builder()
                .message("success")
                .data("Hello World")
                .build();
        return ResponseEntity.ok("Hello World");
    }

}
