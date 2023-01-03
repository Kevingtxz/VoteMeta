package com.meta.vote.main.services;


import com.meta.vote.main.builder.ScheduleFormBuilder;
import com.meta.vote.main.dto.form.ScheduleForm;
import com.meta.vote.main.dto.mapper.ScheduleMapper;
import com.meta.vote.main.entities.ScheduleEntity;
import com.meta.vote.main.repositories.ScheduleRepository;
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
public class ScheduleServiceTest {


    @Mock
    private ScheduleRepository repo;
    @InjectMocks
    private ScheduleService service;
    @Spy
    private ScheduleMapper mapper;


    @Test
    void whenInsertIsCalledWithValidFormThenAEntityBeInserted() {
        ScheduleForm expectedCreatedForm =
                ScheduleFormBuilder.builder().build().toForm();
        ScheduleEntity expectedCreatedEntity =
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
        ScheduleForm expectedDeletedForm =
                ScheduleFormBuilder.builder().build().toForm();
        ScheduleEntity expectedDeletedEntity =
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
