package hackerton.team4.zoombti_backend.controller;

import hackerton.team4.zoombti_backend.domain.JwtTokenProvider;
import hackerton.team4.zoombti_backend.domain.Member;
import hackerton.team4.zoombti_backend.dto.LoginDto;
import hackerton.team4.zoombti_backend.filter.JwtAuthenticationFilter;
import hackerton.team4.zoombti_backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<Object> authorize(@RequestBody LoginDto loginDto) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication); // JWT Token 생성

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        try {
            Member member = memberService.findByEmail(loginDto.getEmail());
        } catch (Exception e) {
            throw new RuntimeException("로그인 정보가 일치하지 않습니다. 다시 시도해주십시오.");
        }
        HashMap<String, Object> res = new HashMap<>(){{
            put("success", true);
            put("token", jwt);
        }};

        return new ResponseEntity<>(res, httpHeaders, org.springframework.http.HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        HashMap<String, Object> res = new HashMap<>(){{
            put("success", false);
            put("message", e.getMessage());
        }};
        return new ResponseEntity<>(res, org.springframework.http.HttpStatus.BAD_REQUEST);
    }
}
