package com.example.demo.repository;
import com.example.demo.model.Artefact;
import com.example.demo.model.EntityObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.List;

@NoRepositoryBean
public interface AnyEntityObjectRepository <E extends EntityObject> extends JpaRepository<E, Long> {

    List<Artefact> findByCategory(String categoryPart);
    List<Artefact> findByUserID(String userID);
    List<Artefact> findByDescription(String description);
    List<Artefact> findByCommentListContaining_Content(String content);
    List<Artefact> findAllByOrderByCreatedAsc();
    List<Artefact> findAllByOrderByCategoryCategoryAsc();
    List<Artefact> findAllByOrderByUserIDUserIDAsc();
}
