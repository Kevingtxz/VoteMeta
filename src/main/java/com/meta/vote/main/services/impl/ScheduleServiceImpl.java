package com.meta.vote.main.services.impl;


import com.meta.vote.main.dto.form.ScheduleForm;
import com.meta.vote.main.dto.mapper.PollMapper;
import com.meta.vote.main.dto.mapper.ScheduleMapper;
import com.meta.vote.main.dto.view.ScheduleView;
import com.meta.vote.main.entities.ScheduleEntity;
import com.meta.vote.main.repositories.ScheduleRepository;
import com.meta.vote.main.services.ScheduleService;
import com.meta.vote.main.services.exceptions.ObjectNotFoundException;
import com.meta.vote.main.utils.enums.RestMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService {


    @Autowired
    private ScheduleRepository repo;
    @Autowired
    private ScheduleMapper mapper;
    @Autowired
    private PollMapper pollMapper;


    public List<ScheduleEntity> findAll() {
        return this.repo.findAll();
    }
    public List<ScheduleView> findAllView() {
        return this.toViewList(this.findAll());
    }
    public ScheduleEntity findById(Integer id) {
        return this.repo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Object id not found."));
    }
    public ScheduleView findByIdView(Integer id) {
        return this.toView(this.findById(id));
    }
    public ScheduleEntity insert(ScheduleEntity entity) {
        entity.setId(null);
        this.repo.save(entity);
        this.useLog(RestMethodEnum.CREATE, entity.getId());
        return entity;
    }
    public ScheduleEntity insert(ScheduleForm form) {
        return this.insert(this.mapper.toEntity(form));
    }
    public void update(ScheduleEntity entity) {
        this.repo.save(entity);
        this.useLog(RestMethodEnum.UPDATE, entity.getId());
    }
    public void update(ScheduleForm form) {
        ScheduleEntity entity = this.findById(form.getId());
        entity.setName(form.getName());
        entity.setDescription(form.getDescription());
        this.update(entity);
    }
    public void delete(Integer id) {
        this.repo.delete(this.findById(id));
        this.useLog(RestMethodEnum.DELETE, id);
    }
    private ScheduleView toView(ScheduleEntity entity) {
        ScheduleView view = this.mapper.toView(entity);
        view.setPollViewList(this.pollMapper.toViewList(entity.getPollEntityList()));
        return view;
    }
    private List<ScheduleView> toViewList(List<ScheduleEntity> listScheduleEntity) {
        return listScheduleEntity.stream()
                .map(obj -> toView(obj))
                .collect(Collectors.toList());
    }
    private void useLog(RestMethodEnum method, Integer id) {
        this.log.info("Object Class: "
                + this.getClass().getName().substring(0, this.getClass().getName().length()-7)
                + ", Action: " + method.getText()
                + ", ID: " + id + ".");
    }

}
