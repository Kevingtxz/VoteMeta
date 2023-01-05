package com.meta.vote.main.dtos.mappers;

import com.meta.vote.main.dtos.forms.AssociateForm;
import com.meta.vote.main.dtos.views.AssociateView;
import com.meta.vote.main.entities.AssociateEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssociateMapper {


    private ModelMapper mapper;


    public AssociateMapper() {
        mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<AssociateEntity, AssociateView>() {
            @Override
            protected void configure(){}
        });
        mapper.addMappings(new PropertyMap<AssociateForm, AssociateEntity>() {
            @Override
            protected void configure(){}
        });
    }


    public AssociateView toView(AssociateEntity entiy) {
        return mapper.map(entiy, AssociateView.class);
    };
    public AssociateEntity toEntity(AssociateForm form){
        return mapper.map(form, AssociateEntity.class);
    }
    public List<AssociateEntity> toEntityList(List<AssociateForm> listForm) {
        return listForm.stream()
                .map(form -> this.toEntity(form))
                .collect(Collectors.toList());
    }
    public List<AssociateView> toViewList(List<AssociateEntity> listEntity) {
        return listEntity.stream()
                .map(obj -> toView(obj))
                .collect(Collectors.toList());
    }

}
