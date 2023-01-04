package com.meta.vote.main.services;


import com.meta.vote.main.builders.PollFormBuilder;
import com.meta.vote.main.dto.form.PollForm;
import com.meta.vote.main.dto.mapper.PollMapper;
import com.meta.vote.main.entities.PollEntity;
import com.meta.vote.main.repositories.PollRepository;
import com.meta.vote.main.services.exceptions.ObjectNotFoundException;
import com.meta.vote.main.services.impl.PollServiceImpl;
import com.meta.vote.main.services.impl.ScheduleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
public class PollServiceTest {


    @Mock
    private PollRepository repo;
    @InjectMocks
    private PollServiceImpl service;
    @Spy
    private PollMapper mapper;
    @Mock
    private ScheduleServiceImpl scheduleService;


    @Test
    void whenInsertIsCalledWithValidFormThenAEntityBeCreated() {
        PollForm expectedCreatedForm =
                PollFormBuilder.builder().build().toForm();
        PollEntity expectedCreatedEntity =
                mapper.toEntity(expectedCreatedForm);

        when(this.repo.save(expectedCreatedEntity))
                .thenReturn(expectedCreatedEntity);

        this.service.insert(expectedCreatedForm);

        verify(this.repo, times(1))
                .save(expectedCreatedEntity);
        verify(this.scheduleService, times(1))
                .findById(expectedCreatedForm.getScheduleEntityId());
    }
    @Test
    void whenDeleteIsCalledWithValidIdThenAEntityBeDeleted()
            throws ObjectNotFoundException {
        PollForm expectedDeletedForm =
                PollFormBuilder.builder().build().toForm();
        PollEntity expectedDeletedEntity =
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
