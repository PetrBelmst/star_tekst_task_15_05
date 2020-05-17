package com.example.demo.controller;

import com.example.demo.exceptions.CreationFailedException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.UpdateFailedException;
import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController implements AnyEntityObjectController <Comment, CommentService>{

    @Autowired
    CommentService commentService;


    @SneakyThrows
    @Override
    public ResponseEntity<Comment> create(Comment object) {
        return commentService.create(object)
                .map(body -> ResponseEntity.ok(body))
                .orElseThrow(() -> new CreationFailedException(String.valueOf(object.getID())));
    }

    @Override
    public ResponseEntity<List<Comment>> readAll() {
        return ResponseEntity.ok(commentService.readAll());
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Comment> read(Long id) {
        return commentService.read(id)
                .map(body -> ResponseEntity.ok(body))
                .orElseThrow(() -> new NotFoundException(id.toString()));
    }

    @Override
    public ResponseEntity<Comment> update(Long id, Comment object) {
        return commentService.update(id, object)
                .map(body -> ResponseEntity.ok(body))
                .orElseThrow(() -> new UpdateFailedException(id.toString()));
    }

    @SneakyThrows
    @Override
    public ResponseEntity<HttpStatus> delete(Long id) {
        commentService.delete(id);
        return commentService.read(id).isPresent()
                ? new ResponseEntity<>(HttpStatus.NOT_MODIFIED)
                : new ResponseEntity<>(HttpStatus.OK);
    }
}
