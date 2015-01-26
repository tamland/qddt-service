package no.nsd.qddt.repository;

import no.nsd.qddt.domain.Concept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Stig Norland
 */
@Repository
public interface ConceptRepository extends RevisionRepository<Concept, Long, Integer>, JpaRepository<Concept, Long> {}