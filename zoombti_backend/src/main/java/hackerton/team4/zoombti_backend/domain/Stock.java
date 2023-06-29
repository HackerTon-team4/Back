package hackerton.team4.zoombti_backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter
public class Stock {
    @Id @Column(length = 6)
    private String stockCode;

    @Column(length = 30)
    private String name;
}
