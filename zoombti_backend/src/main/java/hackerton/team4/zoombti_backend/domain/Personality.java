package hackerton.team4.zoombti_backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter
@Builder
public class Personality {
    @Id
    private Long id;
    @Column(nullable = false, length = 20, unique = true)
    private String mbti;
}
