package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DanhMucEntity extends CommonEntity implements Serializable {
    @Column(name = "category_code")
    private String categoryCode;
    @Column(name = "category_name")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private DanhMucEntity categoryEntity;

    @OneToMany(mappedBy = "categoryEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DanhMucEntity> categoryEntities = new HashSet<>();
}
