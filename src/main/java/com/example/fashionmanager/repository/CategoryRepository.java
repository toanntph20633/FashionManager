package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long>, JpaSpecificationExecutor<CategoryEntity> {
    boolean existsByCategoryCodeAndDeleted(String code,Boolean delete);
    Boolean existsByCategoryCodeAndDeletedAndIdNot(String code,Boolean delete,Long id);

}
