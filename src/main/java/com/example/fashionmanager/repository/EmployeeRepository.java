package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.NhanVienEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<NhanVienEntity,Long> , JpaSpecificationExecutor<NhanVienEntity>{
    boolean existsByCitizenIdentificationCardAndDeleted(String citizenIdentificationCard, Boolean delete);

    boolean existsByCitizenIdentificationCardAndDeletedAndIdNot(String citizenIdentificationCard, Boolean delete, Long id);
}
