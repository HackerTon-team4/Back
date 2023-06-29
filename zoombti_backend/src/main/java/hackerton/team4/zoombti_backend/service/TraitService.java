package hackerton.team4.zoombti_backend.service;

import hackerton.team4.zoombti_backend.domain.Trait;
import hackerton.team4.zoombti_backend.repository.TraitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TraitService {
    private final TraitRepository traitRepository;
    // 사용자의 설문을 Flask 서버에 전달해 성향을 받아온다.
    public Trait getTraitById(Long trait_id) {
        log.info("성향 획득 service");
        // Flask 서버로 Post 요청을 날림

        return traitRepository.findById(trait_id).get();
    }
}
