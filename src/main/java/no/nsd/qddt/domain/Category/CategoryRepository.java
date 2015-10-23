package no.nsd.qddt.domain.category;

import no.nsd.qddt.domain.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Dag Østgulen Heradstveit
 * @author Stig Norland
 */
@Repository
interface CategoryRepository extends BaseRepository<Category,UUID> {
//    @NamedNativeQuery(name = "findUniqueCategoryInOrder", query= "select distinct category as name from Code  order by category", resultClass= Category.class)

    List<Category> findByNameIgnoreCaseContains(String tags);

    Page<Category> findByNameIgnoreCaseContains(String[] tags, Pageable pageable);

//    List<String> findAllCategory();
//
//    Page<String> findAllCategory(Pageable pageable);
}