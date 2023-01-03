package com.meta.vote.main.repositories;

import com.meta.vote.main.entities.AssociateEntity;
import com.meta.vote.main.entities.ScheduleEntity;
import com.meta.vote.main.entities.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Integer> {


//    @Transactional(readOnly = true)
//    List<VoteEntity> findAllByPollEntityId(Integer pollEntityId);
//    @Transactional(readOnly = true)
//    List<VoteEntity> findAllByPollEntityScheduleEntityId(Integer scheduleEntityId);
    @Transactional(readOnly = true)
    boolean existsByAssociateEntityAndPollEntityScheduleEntity(
            AssociateEntity associateEntity, ScheduleEntity scheduleEntity);

}