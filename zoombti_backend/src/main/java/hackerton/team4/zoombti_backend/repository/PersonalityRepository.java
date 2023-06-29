package hackerton.team4.zoombti_backend.repository;

import hackerton.team4.zoombti_backend.domain.Personality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalityRepository extends JpaRepository<Personality, Long> {
    Personality findByMbti(String mbti);
}
