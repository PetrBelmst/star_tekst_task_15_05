package com.example.demo.service;

import com.example.demo.model.EntityObject;

import java.util.List;
import java.util.Optional;

public interface EntityObjectService {
    EntityObject create (EntityObject object);
    List <Optional<EntityObject>> readAll(Enum type);
    Optional<EntityObject> read(Long id);
    Optional<EntityObject> update (Long id);
    void delete (Long id);



}
