package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Table(name = "nha_cung_cap_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class NhaCungCapEntity extends CommonEntity implements Serializable {
    @Column(name = "ma_nha_cung_cap")
    private String maNhaCungCap;
    @Column(name = "ten_nha_cung_cap")
    private String tenNhaCungCap;
}