package hackerton.team4.zoombti_backend.repository;

import hackerton.team4.zoombti_backend.domain.Member;
import org.antlr.v4.runtime.misc.MultiMap;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);

    @EntityGraph(attributePaths = "memberType")
    Optional<Member> findOneWithMemberTypeByEmail(String email);
}
