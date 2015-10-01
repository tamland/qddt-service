package no.nsd.qddt.domain.study.web;

import no.nsd.qddt.domain.study.Study;
import no.nsd.qddt.domain.study.StudyService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Dag Østgulen Heradstveit
 */
@RestController
@RequestMapping(value = "/audit/study/", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudyAuditController {

    private StudyService studyService;

    @Autowired
    public StudyAuditController(StudyService studyService) {
        this.studyService = studyService;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Revision<Integer, Study> getLastRevision(@PathVariable("id") UUID id) {
        return studyService.findLastChange(id);
    }

    @RequestMapping(value = "/{id}/{revision}", method = RequestMethod.GET)
    public Revision<Integer, Study> getByRevision(@PathVariable("id") UUID id, @PathVariable("revision") Integer revision) {
        return studyService.findEntityAtRevision(id, revision);
    }

    @RequestMapping(value = "/{id}/all", method = RequestMethod.GET)
    public HttpEntity<PagedResources<Revision<Integer, Study>>> allProjects(
            @PathVariable("id") UUID id,Pageable pageable, PagedResourcesAssembler assembler){

        Page<Revision<Integer, Study>> studies = studyService.findAllRevisionsPageable(id, pageable);
        return new ResponseEntity<>(assembler.toResource(studies), HttpStatus.OK);
    }

}