package com.example.demo.controller;

import com.example.demo.exceptions.CreationFailedException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.UpdateFailedException;
import com.example.demo.model.Artefact;
import com.example.demo.service.ArtefactService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artefact")
public class ArtefactController implements AnyEntityObjectController <Artefact, ArtefactService>{

    @Autowired
    ArtefactService artefactService;


    @SneakyThrows
    @Override
    public ResponseEntity<Artefact> create(Artefact object) {
        return artefactService.create(object)
                .map(body -> ResponseEntity.ok(body))
                .orElseThrow(() -> new CreationFailedException(String.valueOf(object.getID())));
    }

    @Override
    public ResponseEntity<List<Artefact>> readAll() {
        return ResponseEntity.ok(artefactService.readAll());
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Artefact> read(Long id) {
        return artefactService.read(id)
                .map(body -> ResponseEntity.ok(body))
                .orElseThrow(() -> new NotFoundException(id.toString()));
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Artefact> update(Long id, Artefact object) {
        return artefactService.update(id, object)
                .map(body -> ResponseEntity.ok(body))
                .orElseThrow(() -> new UpdateFailedException(id.toString()));
    }

    @SneakyThrows
    @Override
    public ResponseEntity <HttpStatus> delete(Long id) {
        artefactService.delete(id);
        return artefactService.read(id).isPresent()
                ? new ResponseEntity<>(HttpStatus.NOT_MODIFIED)
                : new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity <List<Artefact>> findByCategory(@RequestParam String category) {
        return ResponseEntity.ok(artefactService.findByCategory(category));
    }

    @GetMapping
    public ResponseEntity <Artefact> findByUserID(@RequestParam String userID){
        return ResponseEntity.ok(artefactService.findByUser(userID));
    }

    @GetMapping
    public ResponseEntity <List<Artefact>> findByDescription(@RequestParam String description){
        return ResponseEntity.ok(artefactService.findByDescription(description));
    }

    @GetMapping
    public ResponseEntity <List<Artefact>> findByCommentListContaining_Content(@RequestParam String content){
        return ResponseEntity.ok(artefactService.findByComment(content));
    }

    @GetMapping("/sort_by_created")
    public ResponseEntity <List<Artefact>> findAllByOrderByCreatedAsc(){
        return ResponseEntity.ok(artefactService.sortByDate());
    }

    @GetMapping("/sort_by_category")
    public ResponseEntity <List<Artefact>> findAllByOrderByCategoryCategoryAsc(){
        return ResponseEntity.ok(artefactService.sortByCategory());
    }

    @GetMapping("/sort_by_user_id")
    public ResponseEntity <List<Artefact>> findAllByOrderByUserIDUserIDAsc(){
        return ResponseEntity.ok(artefactService.sortByUser());
    }





}
