package com.example.demo.repository;
import com.example.demo.model.Artefact;
import com.example.demo.model.EntityObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.List;

@NoRepositoryBean
public interface AnyEntityObjectRepository <E extends EntityObject> extends JpaRepository<E, Long> {

    List<Artefact> findAllByCategory(String category);
    Artefact findByUserID(String userID);
    List<Artefact> findAllByDescription(String description);
    List<Artefact> findAllByCommentListContaining_Content(String content);
    List<Artefact> findByOrderByCreatedAsc();
    List<Artefact> findByOrderByCategoryAsc();
    List<Artefact> findByOrderByUserIDAsc();
}
