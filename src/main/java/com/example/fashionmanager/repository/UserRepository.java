package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.NhanVienEntity;
import com.example.fashionmanager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> , JpaSpecificationExecutor<UserEntity> {
Optional<UserEntity> findByEmail(String userName);

    @Override
    long count();
}
