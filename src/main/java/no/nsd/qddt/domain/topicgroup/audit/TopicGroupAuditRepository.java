package no.nsd.qddt.domain.topicgroup.audit;

import no.nsd.qddt.domain.AbstractEntityAudit;
import no.nsd.qddt.domain.topicgroup.TopicGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.envers.repository.support.EnversRevisionRepository;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Dag Østgulen Heradstveit
 */
@Repository
interface TopicGroupAuditRepository extends EnversRevisionRepository<TopicGroup, UUID, Integer> {

    Page<Revision<Integer,TopicGroup>> findRevisionsByIdAndChangeKindNotIn(UUID uuid, Collection<AbstractEntityAudit.ChangeKind> changeKinds, Pageable pageable);
}
