package no.nsd.qddt.repository;

import no.nsd.qddt.domain.Survey;
import org.springframework.data.envers.repository.support.EnversRevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Dag Østgulen Heradstveit
 */
@Repository
public interface SurveyRepository extends EnversRevisionRepository<Survey, Long, Integer> {

    Optional<Survey> findById(Long id);
}
