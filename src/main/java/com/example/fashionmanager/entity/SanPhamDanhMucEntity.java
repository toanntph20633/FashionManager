package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Table(name = "product_category_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SanPhamDanhMucEntity extends CommonEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "category_id")
    private DanhMucEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private SanPhamEntity sanPhamEntity;
}
