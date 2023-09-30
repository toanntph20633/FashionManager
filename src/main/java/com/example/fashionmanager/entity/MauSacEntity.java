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
@Table(name = "color_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MauSacEntity extends CommonEntity implements Serializable {
    @Column(name = "color_code")
    private String colorCode;
    @Column(name = "color_name")
    private String colorName;
}
