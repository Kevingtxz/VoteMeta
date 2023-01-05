package com.meta.vote.main.services;


import com.meta.vote.main.dtos.forms.VoteForm;
import com.meta.vote.main.dtos.views.VoteView;
import com.meta.vote.main.entities.VoteEntity;

import java.util.List;

public interface VoteService {
    

    List<VoteEntity> findAll();
    List<VoteView> findAllView();
    VoteEntity findById(Integer id);
    VoteView findByIdView(Integer id);
    VoteEntity insert(VoteForm form);
    void delete(Integer id);

}
