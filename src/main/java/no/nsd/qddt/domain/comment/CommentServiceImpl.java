package no.nsd.qddt.domain.comment;

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
@Service("commentService")
class CommentServiceImpl  implements CommentService  {

    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public long count() {
        return commentRepository.count();
    }

    @Override
    public boolean exists(UUID uuid) {
        return commentRepository.exists(uuid);
    }

    @Override
    public Comment findOne(UUID uuid) {
        return commentRepository.findById(uuid).orElseThrow(
                () -> new ResourceNotFoundException(uuid, Comment.class)
        );
    }

    @Override
    @Transactional(readOnly = false)
    public Comment save(Comment instance) {

//        instance.setCreated(LocalDateTime.now());
        return commentRepository.save(instance);
    }

    @Override
    public List<Comment> save(List<Comment> instances) {
        return commentRepository.save(instances);
    }

    @Override
    public void delete(UUID uuid) {
        commentRepository.delete(uuid);
    }

    @Override
    public void delete(List<Comment> instances) {
        commentRepository.delete(instances);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Comment> findAllByOwnerIdPageable(UUID ownerId, Pageable pageable) {
        return commentRepository.findAllByOwnerIdAndIsHiddenOrderByModifiedAsc(ownerId,false, pageable);
    }

}
