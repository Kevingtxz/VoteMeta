package com.meta.vote.main.services;


import com.meta.vote.main.builder.AssociateFormBuilder;
import com.meta.vote.main.dto.form.AssociateForm;
import com.meta.vote.main.dto.mapper.AssociateMapper;
import com.meta.vote.main.entities.AssociateEntity;
import com.meta.vote.main.repositories.AssociateRepository;
import com.meta.vote.main.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AssociateServiceTest {


    @Mock
    private AssociateRepository repo;
    @InjectMocks
    private AssociateService service;
    @Spy
    private AssociateMapper mapper;


    @Test
    void whenInsertIsCalledWithValidFormThenAEntityBeInserted() {
        AssociateForm expectedCreatedForm =
                AssociateFormBuilder.builder().build().toForm();
        AssociateEntity expectedCreatedEntity =
                mapper.toEntity(expectedCreatedForm);
        when(this.repo.save(expectedCreatedEntity))
                .thenReturn(expectedCreatedEntity);

        this.service.insert(expectedCreatedForm);

        verify(this.repo, times(1))
                .save(expectedCreatedEntity);
    }
    @Test
    void whenDeleteIsCalledWithValidIdThenAEntityBeDeleted()
            throws ObjectNotFoundException {
        AssociateForm expectedDeletedForm =
                AssociateFormBuilder.builder().build().toForm();
        AssociateEntity expectedDeletedEntity =
                this.mapper.toEntity(expectedDeletedForm);

        when(this.repo.findById(expectedDeletedForm.getId()))
                .thenReturn(Optional.of(expectedDeletedEntity));
        doNothing().when(this.repo).delete(expectedDeletedEntity);

        this.service.delete(expectedDeletedForm.getId());

        verify(this.repo, times(1))
                .findById(expectedDeletedForm.getId());
        verify(this.repo, times(1))
                .delete(expectedDeletedEntity);
    }

}
