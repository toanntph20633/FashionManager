package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity,Long>, JpaSpecificationExecutor<ColorEntity> {
    boolean existsByColorCodeAndDeleted(String code,Boolean delete);
    boolean existsByColorCodeAndDeletedAndIdNot(String code,Boolean delete ,Long id);
}
