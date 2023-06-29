package hackerton.team4.zoombti_backend.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter
public class LoginDto {
    private String email;
    private String password;


}
