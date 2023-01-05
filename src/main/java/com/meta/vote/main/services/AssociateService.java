package com.meta.vote.main.services;

import com.meta.vote.main.dtos.forms.AssociateForm;
import com.meta.vote.main.dtos.views.AssociateView;
import com.meta.vote.main.entities.AssociateEntity;

import java.util.List;

public interface AssociateService {


    List<AssociateEntity> findAll();
    List<AssociateView> findAllView();
    AssociateEntity findById(Integer id);
    AssociateView findByIdView(Integer id);
    AssociateEntity insert(AssociateForm form);
    void delete(Integer id);

}
