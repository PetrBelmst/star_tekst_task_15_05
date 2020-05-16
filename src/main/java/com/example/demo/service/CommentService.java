package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.model.EntityObject;
import com.example.demo.repository.AnyEntityObjectRepository;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.util.ObjectCopyUtil.deepCopy;

@Service
public class CommentService implements AnyEntityObjectService <Comment, CommentRepository> {

    @Qualifier("commentRepository")
    @Autowired
    private AnyEntityObjectRepository commentRepository;


    @Override
    public EntityObject create(EntityObject object) {
        if (object instanceof Comment) {
            return (Comment) commentRepository.save(object);
        } else throw new IllegalArgumentException();
    }

    @Override
    public List<EntityObject> readAll() {
        List<EntityObject> list = new ArrayList<>();
        commentRepository.findAll().forEach(e -> list.add((Comment) e));
        return list;
    }

    @Override
    public Optional<? extends EntityObject> read(Long id) throws Throwable {
        return (Optional<Comment>) commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Optional<? extends EntityObject> update(Long id, EntityObject objectNew) throws NotFoundException {
        Optional<Comment> objectOriginal;

        if (commentRepository.findById(id).isPresent()) {
            objectOriginal = (Optional <Comment>) commentRepository.findById(id);
            Comment tempForCopy = objectOriginal.get();
            Comment previousVersion = (Comment) deepCopy(objectOriginal);
            previousVersion.setOriginal(false);

            if (!(objectNew instanceof Comment)) {
                throw new IllegalArgumentException();
            }
            else {
                Comment temp = (Comment) objectNew;
                tempForCopy.setArtefact(temp.getArtefact());
                tempForCopy.setArtefactID(temp.getArtefactID());
                tempForCopy.setContent(temp.getContent());
                tempForCopy.setID(temp.getID());
                tempForCopy.setOriginal(temp.isOriginal());
                tempForCopy.setUserID(temp.getUserID());
                commentRepository.saveAndFlush(tempForCopy);
                commentRepository.save(previousVersion);
            }

        } else throw new NotFoundException();
        return commentRepository.findById(((Comment) objectNew).getID());
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
