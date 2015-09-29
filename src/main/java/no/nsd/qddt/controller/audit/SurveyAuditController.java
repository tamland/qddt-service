package no.nsd.qddt.controller.audit;

import no.nsd.qddt.domain.surveyprogram.SurveyProgram;
import no.nsd.qddt.domain.surveyprogram.SurveyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revision;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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
@RequestMapping(value = "/audit/survey/")
public class SurveyAuditController {

    private SurveyProgramService surveyProgramService;

    @Autowired
    public SurveyAuditController(SurveyProgramService surveyProgramService) {
        this.surveyProgramService = surveyProgramService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Revision<Integer, SurveyProgram> findLastRevisionById(@PathVariable("id") UUID id) {
        return surveyProgramService.findLastChange(id);
    }

    @RequestMapping(value = "/{id}/{revision}", method = RequestMethod.GET)
    public Revision<Integer, SurveyProgram> findByRevision(@PathVariable("id") UUID id, @PathVariable("revision") Integer revision) {
        return surveyProgramService.findEntityAtRevision(id, revision);
    }

    @RequestMapping(value = "/{id}/list", method = RequestMethod.GET)
    public HttpEntity<PagedResources<Revision<Integer, SurveyProgram>>> list(
            @PathVariable("id") UUID id,Pageable pageable, PagedResourcesAssembler assembler){

        Page<Revision<Integer, SurveyProgram>> studies = surveyProgramService.findAllRevisionsPageable(id, pageable);
        return new ResponseEntity<>(assembler.toResource(studies), HttpStatus.OK);
    }
}