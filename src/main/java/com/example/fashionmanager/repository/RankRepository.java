package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.RankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<RankEntity, Long>, JpaSpecificationExecutor<RankEntity> {
    boolean existsByRankCodeAndDeleted(String code, Boolean delete);

    boolean existsByRankCodeAndDeletedAndIdNot(String code, Boolean delete, Long id);
}
