package com.meta.vote.main.dtos.mappers;

import com.meta.vote.main.dtos.forms.VoteForm;
import com.meta.vote.main.dtos.views.VoteView;
import com.meta.vote.main.entities.VoteEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoteMapper {


    private ModelMapper mapper;


    public VoteMapper() {
        mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<VoteEntity, VoteView>() {
            @Override
            protected void configure(){}
        });
        mapper.addMappings(new PropertyMap<VoteForm, VoteEntity>() {
            @Override
            protected void configure(){}
        });
    }


    public VoteView toView(VoteEntity entiy) {
        return mapper.map(entiy, VoteView.class);
    }
    public VoteEntity toEntity(VoteForm form){
        return mapper.map(form, VoteEntity.class);
    }
    public List<VoteEntity> toVoteEntityList(List<VoteForm> listForm) {
        return listForm.stream()
                .map(form -> this.toEntity(form))
                .collect(Collectors.toList());
    }
    public List<VoteView> toViewList(List<VoteEntity> listEntity) {
        return listEntity.stream()
                .map(obj -> toView(obj))
                .collect(Collectors.toList());
    }

}
