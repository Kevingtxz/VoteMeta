package com.meta.vote.main.services;


import com.meta.vote.main.dtos.forms.ScheduleForm;
import com.meta.vote.main.dtos.views.ScheduleView;
import com.meta.vote.main.entities.ScheduleEntity;

import java.util.List;

public interface ScheduleService {


    List<ScheduleEntity> findAll();
    List<ScheduleView> findAllView();
    ScheduleEntity findById(Integer id);
    ScheduleView findByIdView(Integer id);
    ScheduleEntity insert(ScheduleForm form);
    void update(ScheduleForm form);
    void delete(Integer id);

}
