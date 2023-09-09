package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface SizeRepository extends JpaRepository<SizeEntity, Long> {
}
