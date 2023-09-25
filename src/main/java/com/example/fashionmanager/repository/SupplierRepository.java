package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.SupplierEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.function.Supplier;
@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long>, JpaSpecificationExecutor<SupplierEntity>{
    boolean existsBySuppilerCodeAndDeleted(String suppilerCode,boolean deleted);
    boolean existsBySuppilerCodeAndDeletedAndIdNot( String code, Boolean delete, Long id);

}
