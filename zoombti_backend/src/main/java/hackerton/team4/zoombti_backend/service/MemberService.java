package hackerton.team4.zoombti_backend.service;

import hackerton.team4.zoombti_backend.domain.Member;
import hackerton.team4.zoombti_backend.dto.MemberDto;
import hackerton.team4.zoombti_backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member join(MemberDto.Request data) {
        log.info("회원가입 서비스");
        Member member = Member.builder()
                .email(data.getEmail())
                .pw(passwordEncoder.encode(data.getPassword()))
                .name(data.getName())
                .build();
        memberRepository.save(member);
        return member;
    }
}
