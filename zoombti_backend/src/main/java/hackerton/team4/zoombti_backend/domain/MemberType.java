package hackerton.team4.zoombti_backend.domain;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class MemberType {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_type", nullable = false)
    private Integer memberType;

    @Column(nullable = false)
    private String roleName;
}
