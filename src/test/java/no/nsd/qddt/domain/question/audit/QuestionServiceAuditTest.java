package no.nsd.qddt.domain.question.audit;

import no.nsd.qddt.domain.AbstractAuditServiceTest;
import no.nsd.qddt.domain.question.Question;
import no.nsd.qddt.domain.question.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Stig Norland
 */
public class QuestionServiceAuditTest extends AbstractAuditServiceTest {

    @Autowired
    private QuestionService codeService;

    @Autowired
    private QuestionAuditService codeAuditService;

    private Question entity;

    @Before
    public void setUp() {


        entity = codeService.save(new Question());

        entity.setName("First");
        entity = codeService.save(entity);
        entity.setName("Second");
        entity = codeService.save(entity);
        entity.setName("Third");
        entity = codeService.save(entity);
    }

    @Test
    public void testSaveSurveyWithAudit() throws Exception {
        entity = codeService.findOne(entity.getId());

        // Find the last revision based on the entity id
        Revision<Integer, Question> revision = codeAuditService.findLastChange(entity.getId());

        // Find all revisions based on the entity id as a page
        Page<Revision<Integer, Question>> revisions = codeAuditService.findRevisions(
                entity.getId(), new PageRequest(0, 10));

        Revisions<Integer, Question> wrapper = new Revisions<>(revisions.getContent());

        assertEquals(wrapper.getLatestRevision().getEntity(), entity);
        assertThat(revisions.getNumberOfElements(), is(4));
    }

    @Test
    public void getAllRevisionsTest() throws Exception {
        Page<Revision<Integer, Question>> revisions =
                codeAuditService.findRevisions(entity.getId(), new PageRequest(0, 20));

        assertEquals("Excepted four revisions.",
                revisions.getNumberOfElements(), 4);
    }

    @Test
    public void getLastRevisionTest() throws Exception {
        Revision<Integer, Question> revision = codeAuditService.findLastChange(entity.getId());

        assertEquals("Excepted initial ResponseDomain Object.",
                revision.getEntity(), entity);
        assertEquals("Expected Name to be 'Third'", revision.getEntity().getName(), "Third");
    }
}
