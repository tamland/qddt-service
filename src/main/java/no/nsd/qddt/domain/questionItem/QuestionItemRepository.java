package no.nsd.qddt.domain.questionItem;

import no.nsd.qddt.domain.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Stig Norland
 */
@Repository
interface QuestionItemRepository extends BaseRepository<QuestionItem,UUID> {

    Page<QuestionItem> findAll(Pageable pageable);

//    @Query("select n from Question n  left join fetch  n.children")
//    Page<Question> getHierarchy(Pageable pageable);

//    @Query("select n from Question n  where n.parent_id is null")
//    Page<Question> getRootElements(Pageable pageable);



//    @Query("select n from Question n left join fetch n.children where ")
//    Page<Question> GetHierarchy(Pageable pageable);


}

