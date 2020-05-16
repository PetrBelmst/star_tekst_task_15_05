package com.example.demo.service;

import com.example.demo.model.EntityObject;
import org.hibernate.exception.DataException;

import java.util.List;
import java.util.Optional;

public interface EntityObjectService {
    EntityObject create (EntityObject object);
    List <Optional<EntityObject>> readAll(Enum type);
    Optional<EntityObject> read(Long id);
    Optional<EntityObject> update (Long id);
    void delete (Long id);

    Optional<EntityObject> findByCategory(String category);
    Optional<EntityObject> findByUser(String userID);
    Optional<EntityObject> findByDescription(String description);
    Optional<EntityObject> findByComment(String content);

    List <EntityObject> sortByDate();
    List <EntityObject> sortByCategory();
    List <EntityObject> sortByUser();

}
