package hackerton.team4.zoombti_backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long uid;
    @Column(unique = true, nullable = false, length = 60)
    String email;
    @Column(nullable = false, length = 256)
    String pw;
    @Column(nullable = false, length = 20)
    String name;
    @ManyToMany
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_uid", referencedColumnName = "uid")},
            inverseJoinColumns = {@JoinColumn(name = "member_type", referencedColumnName = "member_type")})
    @Builder.Default
    private Set<MemberType> memberType = Set.of(MemberType.builder().memberType(1).roleName("USER").build());

    @Builder.Default
    @Column(nullable = false)
    private boolean activated = true;
}
