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
import org.springframework.web.bind.annotation.RequestMapping;
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



}
