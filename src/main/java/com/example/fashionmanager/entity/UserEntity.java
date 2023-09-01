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

@Entity
@Table(name = "user_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserEntity extends CommonEntity {
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;

}
