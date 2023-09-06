package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import jakarta.persistence.Column;
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
@Table(name = "employee_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeEntity extends CommonEntity implements Serializable {
    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "citizen_identification_card")
    private String citizenIdentificationCard;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "city")
    private String city;
    @Column(name = "district")
    private String district;
    @Column(name = "gender")
    private boolean gender;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
