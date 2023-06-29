package hackerton.team4.zoombti_backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter
public class Questionnaire {
    @Id
    private Integer questionIdx;

    @Column
    private Integer ordering;

    @Column(nullable = false, length = 300)
    private String question;

    @Column(nullable = false)
    private String answer1;

    @Column(nullable = false)
    private String answer2;
}
