package com.meta.vote.main.controllers;

import com.meta.vote.main.dto.form.VoteForm;
import com.meta.vote.main.dto.view.VoteView;
import com.meta.vote.main.entities.VoteEntity;
import com.meta.vote.main.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "v1/votes")
public class VoteController {


    @Autowired
    private VoteService service;


//    @GetMapping(value = "poll/{pollEntityId}")
//    public ResponseEntity<List<VoteView>> findAllByPollEntityId(
//            @PathVariable Integer pollEntityId) {
//        List<VoteView> voteViewList = this.service.findAllByPollEntityIdView(pollEntityId);
//        return ResponseEntity.ok().body(voteViewList);
//    }
    @GetMapping
    public ResponseEntity<List<VoteView>> findAll() {
        return ResponseEntity.ok().body(this.service.findAllView());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<VoteView> find(
            @PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .body(this.service.findByIdView(id));
    }
    @PostMapping
    public ResponseEntity<Void> insert(
            @Valid @RequestBody VoteForm form) {
        VoteEntity entity = service.insert(form);
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
