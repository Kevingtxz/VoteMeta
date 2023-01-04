package com.meta.vote.main.services;


import com.meta.vote.main.dto.form.PollForm;
import com.meta.vote.main.dto.view.PollView;
import com.meta.vote.main.entities.PollEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PollService {


    List<PollEntity> findAll();
    List<PollView> findAllView();
    PollEntity findById(Integer id);
    PollView findByIdView(Integer id);
    PollEntity insert(PollForm form);
    PollEntity insert(PollEntity entity);
    void updateDeadline(PollForm form);
    void delete(Integer id);

}
