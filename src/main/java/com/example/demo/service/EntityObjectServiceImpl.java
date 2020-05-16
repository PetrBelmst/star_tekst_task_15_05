package com.example.demo.service;


import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Artefact;
import com.example.demo.model.Comment;
import com.example.demo.model.EntityObject;
import com.example.demo.repository.AnyEntityObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EntityObjectServiceImpl implements EntityObjectService {

    @Qualifier("artefactRepository")
    @Autowired
    private AnyEntityObjectRepository artefactRepository;

    @Qualifier("commentRepository")
    @Autowired
    private AnyEntityObjectRepository commentRepository;


    @Override
    public EntityObject create(EntityObject object) {
        if (object instanceof Artefact) {
            return (EntityObject) artefactRepository.save(object);
        } else if (object instanceof Comment) {
            return (EntityObject) commentRepository.save(object);
        } else throw new IllegalArgumentException();

    }

    @Override
    public List<Optional<? extends EntityObject>> readAll(String type) {
        List<Optional<? extends EntityObject>> list = new ArrayList<>();
        if (type.equalsIgnoreCase("artefact")) {
            artefactRepository.findAll().forEach(e -> list.add((Optional<Artefact>) e));
        } else if (type.equalsIgnoreCase("comment")) {
            commentRepository.findAll().forEach(e -> list.add((Optional<Comment>) e));
        } else {
            throw new IllegalArgumentException();
        }
        return list;
    }

    @Override
    public Optional<? extends EntityObject> read(Long id) {
        return artefactRepository.findById(id) ==  Optional.empty()
               ? commentRepository.findById(id)
               : artefactRepository.findById(id);
    }

    //расширенное задание: для операций UPDATE хранить предыдущие версии объектов
    @Override
    public Optional<? extends EntityObject> update(Long id, EntityObject updObject) throws NotFoundException {
        Optional<? extends EntityObject> object = null;
        if (artefactRepository.findById(id).isPresent()) {
            object = (Optional <Artefact>) artefactRepository.findById(id);
        } else if (commentRepository.findById(id).isPresent()) {
            object = (Optional <Comment>) artefactRepository.findById(id);
        } else throw new NotFoundException();

        return null;

    }


    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<EntityObject> findByCategory(String category) {
        return Optional.empty();
    }

    @Override
    public Optional<EntityObject> findByUser(String userID) {
        return Optional.empty();
    }

    @Override
    public Optional<EntityObject> findByDescription(String description) {
        return Optional.empty();
    }

    @Override
    public Optional<EntityObject> findByComment(String content) {
        return Optional.empty();
    }

    @Override
    public List<EntityObject> sortByDate() {
        return null;
    }

    @Override
    public List<EntityObject> sortByCategory() {
        return null;
    }

    @Override
    public List<EntityObject> sortByUser() {
        return null;
    }


}
