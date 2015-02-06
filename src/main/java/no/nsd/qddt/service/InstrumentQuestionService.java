package no.nsd.qddt.service;

import no.nsd.qddt.domain.instrument.InstrumentQuestion;

import java.util.List;

/**
 * @author Dag Østgulen Heradstveit
 */
public interface InstrumentQuestionService  extends BaseServiceAudit<InstrumentQuestion> {


    public List<InstrumentQuestion> findByInstrumentId(Long instrumentId);

    public List<InstrumentQuestion> findByQuestionId(Long questionId);

}
