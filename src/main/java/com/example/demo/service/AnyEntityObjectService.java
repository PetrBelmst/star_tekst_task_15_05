package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.EntityObject;
import com.example.demo.repository.AnyEntityObjectRepository;

import java.util.List;
import java.util.Optional;

public interface AnyEntityObjectService <E extends EntityObject, R extends AnyEntityObjectRepository> {

    EntityObject create (EntityObject object);
    List<EntityObject> readAll();
    Optional<? extends EntityObject> read(Long id) throws Throwable;
    Optional<? extends EntityObject> update (Long id, EntityObject updObject) throws NotFoundException;
    void delete (Long id);



}
