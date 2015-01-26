package no.nsd.qddt.service;

import no.nsd.qddt.domain.response.Code;
import no.nsd.qddt.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Dag Østgulen Heradstveit
 */
@Service("codeService")
public class CodeServiceImpl implements CodeService {

    private CodeRepository codeRepository;

    @Autowired
    public CodeServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Code findById(Long id) {
        return codeRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Code> findAll() {
        return codeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public Code save(Code code) {
        code.setCreated(LocalDateTime.now());
        return codeRepository.save(code);
    }

    @Override
    @Transactional(readOnly = true)
    public Revision<Integer, Code> findLastChange(Long id) {
        return codeRepository.findLastChangeRevision(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Revision<Integer, Code>> findAllRevisionsPageable(Code code, int min, int max) {
        return codeRepository.findRevisions(code.getId(), new PageRequest(min, max));
    }
}