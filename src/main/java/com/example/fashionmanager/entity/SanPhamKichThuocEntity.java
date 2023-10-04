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
@Table(name = "san_pham_kich_thuoc_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SanPhamKichThuocEntity extends CommonEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "kich_thuoc_id")
    private KichThuocEntity kichThuocEntity;

    @ManyToOne
    @JoinColumn(name = "san_pham_id")
    private SanPhamEntity sanPhamEntity;
}
