package com.example.demo.service;

import com.example.demo.model.EntityObject;

import java.util.List;
import java.util.Optional;

public interface EntityObjectService {
    void create (EntityObject object);
    List <Optional<EntityObject>> readAll();
    Optional<EntityObject> read();
    void update (Long id);
    void delete (Long id);



}
