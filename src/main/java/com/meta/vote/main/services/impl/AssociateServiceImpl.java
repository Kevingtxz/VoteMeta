package com.meta.vote.main.services.impl;


import com.meta.vote.main.dtos.forms.AssociateForm;
import com.meta.vote.main.dtos.mappers.AssociateMapper;
import com.meta.vote.main.dtos.views.AssociateView;
import com.meta.vote.main.entities.AssociateEntity;
import com.meta.vote.main.repositories.AssociateRepository;
import com.meta.vote.main.services.AssociateService;
import com.meta.vote.main.services.exceptions.ObjectNotFoundException;
import com.meta.vote.main.utils.enums.RestMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AssociateServiceImpl implements AssociateService {


    @Autowired
    private AssociateRepository repo;
    @Autowired
    private AssociateMapper mapper;


    public List<AssociateEntity> findAll() {
        return this.repo.findAll();
    }
    public List<AssociateView> findAllView() {
        return this.mapper.toViewList(this.findAll());
    }
    public AssociateEntity findById(Integer id) {
        return this.repo.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Object id not found."));
    }
    public AssociateView findByIdView(Integer id) {
        return this.mapper.toView(this.findById(id));
    }
    public AssociateEntity insert(AssociateForm form) {
        return this.insert(this.mapper.toEntity(form));
    }
    public void delete(Integer id) {
        this.repo.delete(this.findById(id));
        this.useLog(RestMethodEnum.DELETE, id);
    }
    private AssociateEntity insert(AssociateEntity entity) {
        entity.setId(null);
        this.repo.save(entity);
        this.useLog(RestMethodEnum.CREATE, entity.getId());
        return entity;
    }
    private void useLog(RestMethodEnum method, Integer id) {
        this.log.info("Object Class: "
                + this.getClass().getName().substring(0, this.getClass().getName().length()-7)
                + ", Action: " + method.getText()
                + ", ID: " + id + ".");
    }

}
