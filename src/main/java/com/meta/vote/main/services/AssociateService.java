package com.meta.vote.main.services;

import com.meta.vote.main.dto.form.AssociateForm;
import com.meta.vote.main.dto.view.AssociateView;
import com.meta.vote.main.entities.AssociateEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AssociateService {


    List<AssociateEntity> findAll();
    List<AssociateView> findAllView();
    AssociateEntity findById(Integer id);
    AssociateView findByIdView(Integer id);
    AssociateEntity findByEmail(String email);
    AssociateView findByEmailView(String email);
    AssociateEntity insert(AssociateForm form);
    AssociateEntity insert(AssociateEntity entity);
    void delete(Integer id);

}
