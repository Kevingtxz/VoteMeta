package com.meta.vote.main.services;


import com.meta.vote.main.dtos.forms.PollForm;
import com.meta.vote.main.dtos.views.PollView;
import com.meta.vote.main.entities.PollEntity;

import java.util.List;

public interface PollService {


    List<PollEntity> findAll();
    List<PollView> findAllView();
    PollEntity findById(Integer id);
    PollView findByIdView(Integer id);
    PollEntity insert(PollForm form);
    void updateDeadline(PollForm form);
    void delete(Integer id);

}
