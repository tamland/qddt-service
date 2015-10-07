package no.nsd.qddt.domain.surveyprogram.audit;

import no.nsd.qddt.domain.surveyprogram.SurveyProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Dag Østgulen Heradstveit
 */
@Service("surveyProgramAuditService")
class SurveyProgramAuditAuditServiceImpl implements SurveyProgramAuditService {

    private SurveyProgramAuditRepository surveyProgramAuditRepository;

    @Autowired
    public SurveyProgramAuditAuditServiceImpl(SurveyProgramAuditRepository surveyProgramAuditRepository) {
        this.surveyProgramAuditRepository = surveyProgramAuditRepository;
    }

    @Override
    public Revision<Integer, SurveyProgram> findLastChange(UUID uuid) {
        return surveyProgramAuditRepository.findLastChangeRevision(uuid);
    }

    @Override
    public Revision<Integer, SurveyProgram> findRevision(UUID uuid, Integer revision) {
        return surveyProgramAuditRepository.findRevision(uuid, revision);
    }

    @Override
    public Page<Revision<Integer, SurveyProgram>> findRevisions(UUID uuid, Pageable pageable) {
        return surveyProgramAuditRepository.findRevisions(uuid,pageable);
    }
}