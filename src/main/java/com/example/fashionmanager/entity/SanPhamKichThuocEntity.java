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
@Table(name = "product_size_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SanPhamKichThuocEntity extends CommonEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "size_id")
    private KichThuocEntity kichThuocEntity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private SanPhamEntity sanPhamEntity;
}
