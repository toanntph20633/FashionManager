package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.DotGiamGiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DotGiamGiaRepository extends JpaRepository<DotGiamGiaEntity,Long>, JpaSpecificationExecutor<DotGiamGiaEntity> {

}
