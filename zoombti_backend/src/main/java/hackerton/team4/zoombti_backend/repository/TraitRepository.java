package hackerton.team4.zoombti_backend.repository;

import hackerton.team4.zoombti_backend.domain.Trait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraitRepository extends JpaRepository<Trait, Long> {
    Optional<Trait> findTraitByCode(String code);
}
