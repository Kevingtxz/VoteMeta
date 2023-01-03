package com.meta.vote.main.repositories;

import com.meta.vote.main.entities.PollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PollRepository extends JpaRepository<PollEntity, Integer> {}