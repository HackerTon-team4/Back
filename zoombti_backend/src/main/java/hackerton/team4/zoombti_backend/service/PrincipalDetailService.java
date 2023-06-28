package hackerton.team4.zoombti_backend.service;

import hackerton.team4.zoombti_backend.domain.Member;
import hackerton.team4.zoombti_backend.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PrincipalDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member memberEntity = memberRepository.findByEmail(email);

        return memberRepository.findOneWithMemberTypeByEmail(email)
                .map(member -> createUser(email, member))
                .orElseThrow(() -> new UsernameNotFoundException("DB에서 사용자를 찾을 수 없음"));
    }

    private User createUser(String loginId, Member member) {
        if (!member.isActivated()) {
            throw new RuntimeException(loginId + " -> 활성화 되어있지 않음");
        }
        List<GrantedAuthority> grantedAuthorities = member.getMemberType().stream()
                .map(memberType -> (GrantedAuthority) () -> memberType.getRoleName())
                .toList();
        return new User(
                member.getEmail(),
                member.getPw(),
                grantedAuthorities);
    }
}
