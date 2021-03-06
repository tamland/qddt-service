package no.nsd.qddt.domain.study.web;

import no.nsd.qddt.domain.AbstractEntityAudit;
import no.nsd.qddt.domain.study.Study;
import no.nsd.qddt.domain.study.audit.StudyAuditService;
import no.nsd.qddt.domain.surveyprogram.SurveyProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * @author Dag Østgulen Heradstveit
 */
@RestController
@RequestMapping(value = "/audit/study", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudyAuditController {

    private StudyAuditService service;

    @Autowired
    public StudyAuditController(StudyAuditService service) {
        this.service = service;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Revision<Integer, Study> getLastRevision(@PathVariable("id") UUID id) {
        return service.findLastChange(id);
    }

    @RequestMapping(value = "/{id}/{revision}", method = RequestMethod.GET)
    public Revision<Integer, Study> getByRevision(@PathVariable("id") UUID id, @PathVariable("revision") Integer revision) {
        return service.findRevision(id, revision);
    }

    @RequestMapping(value = "/{id}/all", method = RequestMethod.GET)
    public HttpEntity<PagedResources<Revision<Integer, Study>>> allProjects(
            @PathVariable("id") UUID id,
            @RequestParam(value = "ignorechangekinds",defaultValue = "IN_DEVELOPMENT,UPDATED_HIERARCY_RELATION,UPDATED_PARENT")
                    Collection<AbstractEntityAudit.ChangeKind> changekinds,
            Pageable pageable, PagedResourcesAssembler assembler) {

        Page<Revision<Integer, Study>> revisions = service.findRevisionByIdAndChangeKindNotIn(id,changekinds, pageable);
        return new ResponseEntity<>(assembler.toResource(revisions), HttpStatus.OK);
    }

}