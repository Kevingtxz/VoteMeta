package com.meta.vote.main.controllers;

import com.meta.vote.main.dto.form.PollForm;
import com.meta.vote.main.dto.view.PollView;
import com.meta.vote.main.entities.PollEntity;
import com.meta.vote.main.services.impl.PollServiceImpl;
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
    private PollServiceImpl service;


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

    // perguntar sobre como fazer validação de só um pedaço do formulário
    @PatchMapping(value = "/{id}/deadline")
    public ResponseEntity<Void> update(
            @Valid @RequestBody PollForm form, @PathVariable Integer id) {
        form.setId(id);
        this.service.updateDeadline(form);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
