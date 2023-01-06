package com.meta.vote.main.controllers;

import com.meta.vote.main.dtos.forms.AssociateForm;
import com.meta.vote.main.dtos.views.AssociateView;
import com.meta.vote.main.entities.AssociateEntity;
import com.meta.vote.main.services.AssociateService;
import com.meta.vote.main.services.impl.AssociateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/associates")
public class AssociateController {


    @Autowired
    private AssociateService service;


    @GetMapping
    public ResponseEntity<List<AssociateView>> findAll() {
        List<AssociateView> associateViewList = this.service.findAllView();
        return ResponseEntity.ok().body(associateViewList);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<AssociateView> find(
            @PathVariable Integer id) {
        AssociateView view = this.service.findByIdView(id);
        return ResponseEntity.ok().body(view);
    }
    @PostMapping
    public ResponseEntity<Void> insert(
            @Valid @RequestBody AssociateForm form) {
        AssociateEntity entity = service.insert(form);
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
