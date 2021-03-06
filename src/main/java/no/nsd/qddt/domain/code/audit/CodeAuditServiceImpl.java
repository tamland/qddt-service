package no.nsd.qddt.domain.code.audit;

import no.nsd.qddt.domain.AbstractEntityAudit;
import no.nsd.qddt.domain.code.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Dag Østgulen Heradstveit
 */
@Service("codeAuditService")
class CodeAuditServiceImpl implements CodeAuditService {

    private CodeAuditRepository codeAuditRepository;

    @Autowired
    public CodeAuditServiceImpl(CodeAuditRepository codeAuditRepository) {
        this.codeAuditRepository = codeAuditRepository;
    }

    @Override
    public Revision<Integer, Code> findLastChange(UUID uuid) {
        return codeAuditRepository.findLastChangeRevision(uuid);
    }

    @Override
    public Revision<Integer, Code> findRevision(UUID uuid, Integer revision) {
        return codeAuditRepository.findRevision(uuid, revision);
    }

    @Override
    public Page<Revision<Integer, Code>> findRevisions(UUID uuid, Pageable pageable) {
        return codeAuditRepository.findRevisions(uuid, pageable);
    }

//    @Override
//    public Page<Revision<Integer, Code>> findRevisionByIdAndChangeKindNotIn(UUID id, Collection<AbstractEntityAudit.ChangeKind> changeKinds, Pageable pageable) {
//        return null;
//    }

}