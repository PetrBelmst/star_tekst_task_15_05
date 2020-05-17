package com.example.demo.controller;

import com.example.demo.model.EntityObject;
import com.example.demo.service.AnyEntityObjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AnyEntityObjectController <E extends EntityObject, S extends AnyEntityObjectService>{

    @PostMapping
    ResponseEntity<E> create (@RequestBody E object);

    @GetMapping("/all")
    ResponseEntity <List<E>> readAll();

    @GetMapping
    ResponseEntity <E> read(@RequestParam Long id);

    @PutMapping
    ResponseEntity <E> update (@RequestParam Long id, @RequestBody E object);

    @DeleteMapping
    ResponseEntity <HttpStatus> delete (@RequestParam Long id);


}
