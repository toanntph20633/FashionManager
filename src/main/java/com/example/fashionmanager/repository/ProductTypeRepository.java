package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.ProducerEntity;
import com.example.fashionmanager.entity.ProductTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long>, JpaSpecificationExecutor<ProductTypeEntity> {
    boolean existsByProductTypeAndDeleted(String code, Boolean delete);
    boolean existsByProductTypeAndDeletedAndIdNot(String code, Boolean delete, Long id);

}
