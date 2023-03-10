package com.meta.vote.main.controllers;

import com.meta.vote.main.dtos.forms.ScheduleForm;
import com.meta.vote.main.dtos.views.ScheduleView;
import com.meta.vote.main.entities.ScheduleEntity;
import com.meta.vote.main.services.ScheduleService;
import com.meta.vote.main.services.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/schedules")
public class ScheduleController {


    @Autowired
    private ScheduleService service;


    @GetMapping
    public ResponseEntity<List<ScheduleView>> findAll() {
        return ResponseEntity.ok().body(this.service.findAllView());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ScheduleView> find(
            @PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .body(this.service.findByIdView(id));
    }
    @PostMapping
    public ResponseEntity<Void> insert(
            @Valid @RequestBody ScheduleForm form) {
        ScheduleEntity entity = service.insert(form);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(
            @Valid @RequestBody ScheduleForm form
            , @PathVariable Integer id) {
        form.setId(id);
        service.update(form);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
