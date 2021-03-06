package no.nsd.qddt.domain.concept;

import no.nsd.qddt.domain.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Stig Norland
 */
@Repository
interface ConceptRepository extends BaseRepository<Concept,UUID> {

    Page<Concept> findAll(Pageable pageable);

    Page<Concept> findByTopicGroupIdAndNameIsNotNull(UUID id, Pageable pageable);

    Page<Concept> findByTopicGroupIdAndNameIsNull(UUID id, Pageable pageable);

    List<Concept> findByQuestionItemsId(UUID questionItemId);
}
