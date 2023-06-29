package hackerton.team4.zoombti_backend.dto;

import hackerton.team4.zoombti_backend.domain.Stock;
import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TraitDto {
    private Long jbti;
    private String mbti;
    private List<Stock> stocks;
}
