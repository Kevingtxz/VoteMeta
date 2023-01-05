package com.meta.vote.main.services.impl;


import com.meta.vote.main.dtos.forms.PollForm;
import com.meta.vote.main.dtos.mappers.PollMapper;
import com.meta.vote.main.dtos.views.PollView;
import com.meta.vote.main.entities.PollEntity;
import com.meta.vote.main.entities.ScheduleEntity;
import com.meta.vote.main.repositories.PollRepository;
import com.meta.vote.main.services.PollService;
import com.meta.vote.main.services.exceptions.ObjectNotFoundException;
import com.meta.vote.main.utils.enums.RestMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PollServiceImpl implements PollService {


    @Autowired
    private PollRepository repo;
    @Autowired
    private PollMapper mapper;


    public List<PollEntity> findAll() {
        return this.repo.findAll();
    }
    public List<PollView> findAllView() {
        return this.mapper.toViewList(this.findAll());
    }
    public PollEntity findById(Integer id) {
        return this.repo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Object id not found."));
    }
    public PollView findByIdView(Integer id) {
        return this.mapper.toView(this.findById(id));
    }
    public PollEntity insert(PollForm form) {
        PollEntity entity = this.mapper.toEntity(form);
        entity.setScheduleEntity(new ScheduleEntity());
        entity.getScheduleEntity().setId(form.getScheduleEntityId());
        return this.insert(entity);
    }
    public void updateDeadline(PollForm form) {
        PollEntity entity = this.findById(form.getId());
        entity.setDeadline(form.getDeadline());
        this.update(RestMethodEnum.PATCH, entity);
    }
    public void delete(Integer id) {
        this.repo.delete(this.findById(id));
        this.useLog(RestMethodEnum.DELETE, id);
    }
    @Transactional
    private PollEntity insert(PollEntity entity) {
        entity.setId(null);
        this.repo.save(entity);
        this.useLog(RestMethodEnum.CREATE, entity.getId());
        return entity;
    }
    private PollEntity update(RestMethodEnum restMethodEnum, PollEntity entity) {
        this.repo.save(entity);
        this.useLog(restMethodEnum, entity.getId());
        return entity;
    }
    private void useLog(RestMethodEnum method, Integer id) {
        this.log.info("Object Class: "
                + this.getClass().getName().substring(0, this.getClass().getName().length()-7)
                + ", Action: " + method.getText()
                + ", ID: " + id + ".");
    }

}
