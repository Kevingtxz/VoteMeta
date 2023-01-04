package com.meta.vote.main.services;


import com.meta.vote.main.dto.form.VoteForm;
import com.meta.vote.main.dto.view.VoteView;
import com.meta.vote.main.entities.VoteEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VoteService {
    

    List<VoteEntity> findAll();
    List<VoteView> findAllView();
    VoteEntity findById(Integer id);
    VoteView findByIdView(Integer id);
    VoteEntity insert(VoteForm form);
    VoteEntity insert(VoteEntity entity);
    void delete(Integer id);

}
