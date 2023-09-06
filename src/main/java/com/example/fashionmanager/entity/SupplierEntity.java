package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "supplier_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierEntity extends CommonEntity implements Serializable {
    @Column(name = "suppiler_code")
    private String suppilerCode;
    @Column(name = "suppiler_name")
    private String supplierName;
}
