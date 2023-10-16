package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
Optional<UserEntity> findByEmail(String userName);

    @Override
    long count();
}
