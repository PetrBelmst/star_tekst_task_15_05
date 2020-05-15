package com.example.demo.service;

import com.example.demo.model.EntityObject;

import java.util.List;
import java.util.Optional;

public class CommentService implements AnyEntityObjectService{
    @Override
    public void create(EntityObject object) {

    }

    @Override
    public List<Optional<EntityObject>> readAll() {
        return null;
    }

    @Override
    public Optional<EntityObject> read() {
        return Optional.empty();
    }

    @Override
    public void update() {

    }

    @Override
    public void delete(Long id) {

    }
}
