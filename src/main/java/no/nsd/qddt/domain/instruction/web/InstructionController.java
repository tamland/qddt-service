package no.nsd.qddt.domain.instruction.web;

import no.nsd.qddt.domain.HierarchyLevel;
import no.nsd.qddt.domain.category.Category;
import no.nsd.qddt.domain.category.CategoryType;
import no.nsd.qddt.domain.instruction.Instruction;
import no.nsd.qddt.domain.instruction.InstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Dag Østgulen Heradstveit
 * @author Stig Norland
 */
@RestController
@RequestMapping("/instruction")
public class InstructionController {

    private InstructionService instructionService;

    @Autowired
    public InstructionController(InstructionService instructionService) {
        this.instructionService = instructionService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Instruction get(@PathVariable("id") UUID id) {
        return instructionService.findOne(id);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Instruction update(@RequestBody Instruction instruction) {
        return instructionService.save(instruction);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Instruction create(@RequestBody Instruction instruction) {
        return instructionService.save(instruction);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public void delete(@PathVariable("id") UUID id) {
        instructionService.delete(id);
    }



    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/page/search", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<PagedResources<Instruction>> getBy(@RequestParam(value = "description",defaultValue = "%") String description,
                                                      Pageable pageable, PagedResourcesAssembler assembler) {
        Page<Instruction> instructions = null;
        description = description.replace("*","%");

        instructions = instructionService.findByDescriptionLike(description, pageable);
        return new ResponseEntity<>(assembler.toResource(instructions), HttpStatus.OK);
    }
}

