package com.meta.vote.main.dto.mapper;

import com.meta.vote.main.dto.form.PollForm;
import com.meta.vote.main.dto.view.PollView;
import com.meta.vote.main.entities.PollEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PollMapper {


    private ModelMapper mapper;


    public PollMapper() {
        mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<PollEntity, PollView>() {
            @Override
            protected void configure(){}
        });
        mapper.addMappings(new PropertyMap<PollForm, PollEntity>() {
            @Override
            protected void configure(){}
        });
    }


    public PollView toView(PollEntity entiy) {
        return mapper.map(entiy, PollView.class);
    }
    public PollEntity toEntity(PollForm form){
        return mapper.map(form, PollEntity.class);
    }
    public List<PollEntity> toEntityList(List<PollForm> listForm) {
        return listForm.stream()
                .map(form -> this.toEntity(form))
                .collect(Collectors.toList());
    }
    public List<PollView> toViewList(List<PollEntity> listEntity) {
        return listEntity.stream()
                .map(entity -> toView(entity))
                .collect(Collectors.toList());
    }

}
