package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Artefact;
import com.example.demo.model.EntityObject;
import com.example.demo.repository.AnyEntityObjectRepository;
import com.example.demo.repository.ArtefactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.util.ObjectCopyUtil.deepCopy;

@Service
public class ArtefactService implements AnyEntityObjectService <Artefact, ArtefactRepository> {

    @Qualifier("artefactRepository")
    @Autowired
    private AnyEntityObjectRepository artefactRepository;


    @Override
    public EntityObject create (EntityObject object) {
        if (object instanceof Artefact) {
            return (Artefact) artefactRepository.save(object);
        } else throw new IllegalArgumentException();

    }

    @Override
    public List<EntityObject> readAll () {
        List<EntityObject> list = new ArrayList<>();
        artefactRepository.findAll().forEach(e -> list.add((Artefact) e));
        return list;
    }

    @Override
    public Optional<? extends EntityObject> read (Long id) throws Throwable {
        return (Optional<Artefact>) artefactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    //расширенное задание: для операций UPDATE хранить предыдущие версии объектов
    @Override
    public Optional<? extends EntityObject> update(Long id, EntityObject objectNew) throws NotFoundException {
        Optional<Artefact> objectOriginal;

        if (artefactRepository.findById(id).isPresent()) {
            objectOriginal = (Optional <Artefact>) artefactRepository.findById(id);
            Artefact tempForCopy = objectOriginal.get();
            Artefact previousVersion = (Artefact) deepCopy(objectOriginal);
            previousVersion.setOriginal(false);

            if (!(objectNew instanceof Artefact)) {
                throw new IllegalArgumentException();
            }
            else {
                Artefact temp = (Artefact) objectNew;
                tempForCopy.setCategory(temp.getCategory());
                tempForCopy.setCommentList(temp.getCommentList());
                tempForCopy.setCreated(temp.getCreated());
                tempForCopy.setDescription(temp.getDescription());
                tempForCopy.setID(temp.getID());
                tempForCopy.setOriginal(temp.isOriginal());
                tempForCopy.setUserID(temp.getUserID());
                artefactRepository.saveAndFlush(tempForCopy);
                artefactRepository.save(previousVersion);
            }

        } else throw new NotFoundException();
        return artefactRepository.findById(((Artefact) objectNew).getID());
    }

    @Override
    public void delete(Long id) {
        artefactRepository.deleteById(id);
    }


    public Optional<EntityObject> findByCategory(String category) {
        return Optional.empty();
    }


    public Optional<EntityObject> findByUser(String userID) {
        return Optional.empty();
    }


    public Optional<EntityObject> findByDescription(String description) {
        return Optional.empty();
    }


    public Optional<EntityObject> findByComment(String content) {
        return Optional.empty();
    }


    public List<EntityObject> sortByDate() {
        return null;
    }


    public List<EntityObject> sortByCategory() {
        return null;
    }


    public List<EntityObject> sortByUser() {
        return null;
    }


}
