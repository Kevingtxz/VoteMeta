package com.meta.vote.main.services.impl;


import com.meta.vote.main.dto.form.AssociateForm;
import com.meta.vote.main.dto.mapper.AssociateMapper;
import com.meta.vote.main.dto.view.AssociateView;
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
    public AssociateEntity findByEmail(String email) {
        return this.repo.findByEmail(email)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Object id not found. Email does not exist."));
    }
    public AssociateView findByEmailView(String email) {
        return this.mapper.toView(this.findByEmail(email));
    }
    public AssociateEntity insert(AssociateForm form) {
        return this.insert(this.mapper.toEntity(form));
    }
    public AssociateEntity insert(AssociateEntity entity) {
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
