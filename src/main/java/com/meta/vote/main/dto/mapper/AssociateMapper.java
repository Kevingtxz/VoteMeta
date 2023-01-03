package com.meta.vote.main.dto.mapper;

import com.meta.vote.main.dto.form.AssociateForm;
import com.meta.vote.main.dto.view.AssociateView;
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
    public List<AssociateEntity> toAssociateEntityList(List<AssociateForm> listAssociateForm) {
        return listAssociateForm.stream()
                .map(form -> this.toEntity(form))
                .collect(Collectors.toList());
    }
    public List<AssociateView> toViewList(List<AssociateEntity> listAssociateEntity) {
        return listAssociateEntity.stream()
                .map(obj -> toView(obj))
                .collect(Collectors.toList());
    }

}
