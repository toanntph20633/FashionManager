package com.example.fashionmanager.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(CommonEmtityListener.class)
public class CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "status_live")
    private int statusLive;
    @Column(name = "status_action")
    private int statusAction;
    @Column(name = "create_date")
    private LocalDate dateCreate;
    @Column(name = "update_date")
    private LocalDate dateUpdate;
}
