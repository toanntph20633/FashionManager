package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "product_size_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSizeEntity extends CommonEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "size_id")
    private SizeEntity sizeEntity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
}
