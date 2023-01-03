package com.meta.vote.main.services;


import com.meta.vote.main.dto.form.ScheduleForm;
import com.meta.vote.main.dto.view.ScheduleView;
import com.meta.vote.main.entities.ScheduleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {


    List<ScheduleEntity> findAll();
    List<ScheduleView> findAllView();
    ScheduleEntity findById(Integer id);
    ScheduleView findByIdView(Integer id);
    ScheduleEntity insert(ScheduleEntity entity);
    ScheduleEntity insert(ScheduleForm form);
    void update(ScheduleEntity entity);
    void update(ScheduleForm form);
    void delete(Integer id);
    ScheduleView toView(ScheduleEntity entity);
    List<ScheduleView> toViewList(List<ScheduleEntity> listScheduleEntity);

}
