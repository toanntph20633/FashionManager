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
@Table(name = "cau_truc_khuy_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CauTrucKhuyEntity  extends CommonEntity implements Serializable {
    @Column(name = "ten_cau_truc_khuy")
    private String tenCauTrucKhuy;
    @Column(name = "mo_ta", columnDefinition = "LONGTEXT")
    private String moTa;
}
