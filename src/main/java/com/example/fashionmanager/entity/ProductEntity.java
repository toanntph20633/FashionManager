package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import com.example.fashionmanager.enums.DiscountType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "image_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity extends CommonEntity implements Serializable {
    @Column(name = "product_code")
    private String productCode;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "original_price")
    private BigDecimal originalPrice;
    @Column(name = "starting_price")
    private BigDecimal startingPrice;
    @Column(name = "discount_type")
    @Enumerated(EnumType.STRING)
    private DiscountType productDiscountType;
    @Column(name = "discount_value")
    private BigDecimal discountValue;

    @Column(name = "final_price")
    private BigDecimal finalPrice;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductTypeEntity productTypeEntity;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private ProducerEntity producerEntity;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierEntity;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductCategoryEntity> productCategoryEntities = new HashSet<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductColorEntity> productColorEntities = new HashSet<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductSizeEntity> productSizeEntities = new HashSet<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ImageEntity> imageEntities = new HashSet<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ReviewEntity> reviewEntities = new HashSet<>();


}
