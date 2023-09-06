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
@Table(name = "rank_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankEntity extends CommonEntity implements Serializable {
    @Column(name = "rank_code")
    private String rankCode;
    @Column(name = "rank_name")
    private String rankName;
}
