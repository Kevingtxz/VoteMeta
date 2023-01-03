package com.meta.vote.main.services;


import com.meta.vote.main.dto.form.VoteForm;
import com.meta.vote.main.dto.mapper.VoteMapper;
import com.meta.vote.main.dto.view.VoteView;
import com.meta.vote.main.entities.VoteEntity;
import com.meta.vote.main.repositories.VoteRepository;
import com.meta.vote.main.services.exceptions.DataIntegrityException;
import com.meta.vote.main.services.exceptions.ObjectNotFoundException;
import com.meta.vote.main.utils.enums.RestMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class VoteService {


    @Autowired
    private VoteRepository repo;
    @Autowired
    private VoteMapper mapper;
    @Autowired
    private AssociateService associateService;
    @Autowired
    private PollService pollService;


//    public List<VoteEntity> findAllByPollEntityId(Integer pollEntityId) {
//        return this.repo.findAllByPollEntityId(pollEntityId);
//    }
//    public List<VoteView> findAllByPollEntityIdView(Integer pollEntityId) {
//        return this.mapper.toViewList(this.findAllByPollEntityId(pollEntityId));
//    }
//    public List<VoteEntity> findAllByPollEntityScheduleEntityId(Integer scheduleEntityId) {
//        return this.repo.findAllByPollEntityScheduleEntityId(scheduleEntityId);
//    }
//    public List<VoteView> findAllByPollEntityScheduleEntityIdView(Integer scheduleEntityId) {
//        return this.mapper.toViewList(this.findAllByPollEntityScheduleEntityId(scheduleEntityId));
//    }
    public List<VoteEntity> findAll() {
        return this.repo.findAll();
    }
    public List<VoteView> findAllView() {
        return this.mapper.toViewList(this.findAll());
    }
    public VoteEntity findById(Integer id) {
        return this.repo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Object id not found."));
    }
    public VoteView findByIdView(Integer id) {
        return this.mapper.toView(this.findById(id));
    }
    public VoteEntity insert(VoteForm form) {
        VoteEntity entity = this.mapper.toEntity(form);
        entity.setPollEntity(this.pollService.findById(form.getPollEntityId()));
        entity.setAssociateEntity(this.associateService.findById(form.getAssociateEntityId()));
        return this.insert(entity);
    }
    @Transactional
    public VoteEntity insert(VoteEntity entity) {
        if (entity.getPollEntity().isFinished()) {
            throw new DataIntegrityException("This Poll is closed.");
        } else if (this.repo.existsByAssociateEntityAndPollEntityScheduleEntity(
                entity.getAssociateEntity()
                , entity.getScheduleEntity())) {
            throw new DataIntegrityException("Associate can't vote two times for the same schedule.");
        }
        this.repo.save(entity);
        this.useLog(RestMethodEnum.CREATE, entity.getId());
        return entity;
    }
    public void delete(Integer id) {
        this.repo.delete(this.findById(id));
        this.useLog(RestMethodEnum.DELETE, id);
    }
    private void useLog(RestMethodEnum method, Integer id) {
        this.log.info("Object Class: "
                + this.getClass().getName().substring(0, this.getClass().getName().length()-7)
                + ", Action: " + method.getText()
                + ", ID: " + id + ".");
    }

}
