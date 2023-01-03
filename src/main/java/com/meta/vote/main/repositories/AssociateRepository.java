package com.meta.vote.main.repositories;

import com.meta.vote.main.entities.AssociateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AssociateRepository extends JpaRepository<AssociateEntity, Integer> {


    @Transactional(readOnly = true)
    Optional<AssociateEntity> findByEmail(String email);
    @Transactional(readOnly = true)
    Optional<AssociateEntity> findByCpf(String cpf);
    @Transactional(readOnly = true)
    boolean existsByEmail(String email);

}