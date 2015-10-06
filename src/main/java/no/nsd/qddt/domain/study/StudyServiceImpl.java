package no.nsd.qddt.domain.study;

import no.nsd.qddt.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Dag Østgulen Heradstveit
 * @author Stig Norland
 */
@Service("studyService")
class StudyServiceImpl implements StudyService {

    private StudyRepository studyRepository;

    @Autowired
    public StudyServiceImpl(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public long count() {
        return studyRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(UUID uuid) {
        return studyRepository.exists(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public Study findOne(UUID uuid) {
        return studyRepository.findById(uuid).orElseThrow(
                () -> new ResourceNotFoundException(uuid, Study.class)
        );    }

    @Override
    @Transactional(readOnly = true)
    public List<Study> findAll() {
        return studyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Study> findAll(Pageable pageable) {
        return studyRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Study> findAll(Iterable<UUID> uuids) {
        return studyRepository.findAll(uuids);
    }

    @Override
    @Transactional(readOnly = false)
    public Study save(Study instance) {

        instance.setCreated(LocalDateTime.now());
        return studyRepository.save(instance);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(UUID uuid) {
        studyRepository.delete(uuid);
    }
}
