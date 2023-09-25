package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<ProducerEntity, Long>, JpaSpecificationExecutor<ProducerEntity> {

    boolean existsByProducerCodeAndDeleted(String code, Boolean deleted);

    boolean existsByProducerCodeAndDeletedAndIdNot(String code, Boolean deleted, Long id);
}
