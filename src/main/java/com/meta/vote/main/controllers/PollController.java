package com.meta.vote.main.controllers;

import com.meta.vote.main.dto.form.PollForm;
import com.meta.vote.main.dto.view.PollView;
import com.meta.vote.main.entities.PollEntity;
import com.meta.vote.main.services.PollService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "v1/polls")
public class PollController {


    @Autowired
    private PollService service;


    @GetMapping
    public ResponseEntity<List<PollView>> findAll() {
        return ResponseEntity.ok().body(this.service.findAllView());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<PollView> find(
            @PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .body(this.service.findByIdView(id));
    }
    @PostMapping
    public ResponseEntity<Void> insert(
            @Valid @RequestBody PollForm form) {
        PollEntity entity = service.insert(form);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
