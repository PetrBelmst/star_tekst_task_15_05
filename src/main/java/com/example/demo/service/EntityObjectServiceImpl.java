package com.example.demo.service;

import static com.example.demo.model.EntityType.ARTEFACT;
import static com.example.demo.model.EntityType.COMMENT;

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
    public List<Optional<EntityObject>> readAll(Enum type) {
        List<Optional<EntityObject>> list = new ArrayList<>();
        if (ARTEFACT.equals(type)) {
            artefactRepository.findAll().forEach(e -> list.add((Optional<EntityObject>) e));
        } else if (COMMENT.equals(type)) {
            commentRepository.findAll().forEach(e -> list.add((Optional<EntityObject>) e));
        } else {
            throw new IllegalArgumentException();
        }
        return list;
    }

    @Override
    public Optional<EntityObject> read(Long id) {
        return artefactRepository.findById(id) ==  Optional.empty()
               ? commentRepository.findById(id)
               : artefactRepository.findById(id);
    }

    @Override
    public Optional<EntityObject> update(Long id) {

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
