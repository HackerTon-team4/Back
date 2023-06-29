package hackerton.team4.zoombti_backend.controller;

import hackerton.team4.zoombti_backend.domain.Member;
import hackerton.team4.zoombti_backend.dto.MemberDto;
import hackerton.team4.zoombti_backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody MemberDto.Request data){
        log.info("회원가입 : member = {}", data);
        Member saved;
        try {
            saved = memberService.join(data);
        } catch (Exception e) {
            throw new DuplicateKeyException("이미 존재하는 이메일입니다.");
        }

        MemberDto.Response response = MemberDto.Response.builder()
                .email(saved.getEmail())
                .name(saved.getName())
                .build();

        HashMap<String, Object> result = new HashMap<>(){{
            put("success", true);
            put("data", response);
        }};

        return ResponseEntity.ok(result);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException e) {
        HashMap<String, Object> res = new HashMap<>(){{
            put("success", false);
            put("message", e.getMessage());
        }};
        return new ResponseEntity<>(res, org.springframework.http.HttpStatus.BAD_REQUEST);
    }
}
