package com.meta.vote.main.dtos.mappers;

import com.meta.vote.main.dtos.forms.ScheduleForm;
import com.meta.vote.main.dtos.views.ScheduleView;
import com.meta.vote.main.entities.ScheduleEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleMapper {


    private ModelMapper mapper;


    public ScheduleMapper() {
        mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<ScheduleEntity, ScheduleView>() {
            @Override
            protected void configure(){}
        });
        mapper.addMappings(new PropertyMap<ScheduleForm, ScheduleEntity>() {
            @Override
            protected void configure(){}
        });
    }


    public ScheduleView toView(ScheduleEntity entiy) {
        return mapper.map(entiy, ScheduleView.class);
    }
    public ScheduleEntity toEntity(ScheduleForm form){
        return mapper.map(form, ScheduleEntity.class);
    }
    public List<ScheduleEntity> toScheduleEntityList(List<ScheduleForm> listScheduleForm) {
        return listScheduleForm.stream()
                .map(form -> this.toEntity(form))
                .collect(Collectors.toList());
    }
    public List<ScheduleView> toViewList(List<ScheduleEntity> listScheduleEntity) {
        return listScheduleEntity.stream()
                .map(obj -> toView(obj))
                .collect(Collectors.toList());
    }

}
