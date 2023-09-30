package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.Column;
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
import java.time.LocalDate;

@Entity
@Table(name = "customer_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class KhachHangEntity extends CommonEntity implements Serializable {
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "city")
    private String city;
    @Column(name = "district")
    private String district;
    @Column(name = "detail_address")
    private String detailAddress;
    @Column(name = "email")
    private String email;
    @Column(name = "customer_date")
    private LocalDate date;
    @Column(name = "point_accumulated")
    private Long pointAccumulated;
    @ManyToOne
    @JoinColumn(name = "rank_id")
    private HangEntity hangEntity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
