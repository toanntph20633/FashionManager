package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import com.example.fashionmanager.enums.ReviewPoint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "review_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewEntity extends CommonEntity implements Serializable {
    @Column(name = "review_point")
    @Enumerated(EnumType.STRING)
    private ReviewPoint reviewPoint;
    @Column(name = "reviewer_name")
    private String reviewName;
    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
}
