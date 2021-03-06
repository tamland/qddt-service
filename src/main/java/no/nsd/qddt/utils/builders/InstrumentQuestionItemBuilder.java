package no.nsd.qddt.utils.builders;

import no.nsd.qddt.domain.instrument.Instrument;
import no.nsd.qddt.domain.controlconstruct.ControlConstruct;
import no.nsd.qddt.domain.questionItem.QuestionItem;

/**
 * @author Dag Østgulen Heradstveit
 */
public class InstrumentQuestionItemBuilder {

    private String changeComment;
    private String name;
    private Instrument instrument;
    private QuestionItem questionItem;


    public InstrumentQuestionItemBuilder setChangeComment(String changeComment) {
        this.changeComment = changeComment;
        return this;
    }

    public InstrumentQuestionItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public InstrumentQuestionItemBuilder setInstrument(Instrument instrument) {
        this.instrument = instrument;
        return this;
    }

    public InstrumentQuestionItemBuilder setQuestion(QuestionItem question) {
        this.questionItem = question;
        return this;
    }

    public ControlConstruct createInstrument() {
        ControlConstruct controlConstruct = new ControlConstruct();
        controlConstruct.setName(this.name);
        controlConstruct.setChangeComment(this.changeComment);
        controlConstruct.setInstrument(this.instrument);
        controlConstruct.setQuestionItem(this.questionItem);

        return controlConstruct;
    }
}
