package hackerton.team4.zoombti_backend.service;

import hackerton.team4.zoombti_backend.domain.Personality;
import hackerton.team4.zoombti_backend.repository.PersonalityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersonalityService {
    private final PersonalityRepository personalityRepository;

    public Personality getPersonalityByMbti(String mbti) {
        log.info("성격 획득 service");
        return personalityRepository.findByMbti(mbti);
    }
}
