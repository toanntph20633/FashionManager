//package com.example.fashionmanager.entity;
//
//import com.example.fashionmanager.entity.common.CommonEntity;
//import com.example.fashionmanager.enums.HistoryMethod;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.SuperBuilder;
//
//import java.io.Serializable;
//
//@Entity
//@Table(name = "lich_su_tuong_tac_san_pham_entity")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@SuperBuilder
//public class LichSuTuongTacSanPhamEntity extends CommonEntity implements Serializable {
//    @ManyToOne
//    @JoinColumn(name = "san_pham_id")
//    private SanPhamEntity sanPhamEntity;
//    @ManyToOne
//    @JoinColumn(name = "nhan_vien_id")
//    private NhanVienEntity nhanVienEntity;
//
//    @Column(name = "thao_tac")
//    @Enumerated(EnumType.STRING)
//    private HistoryMethod thaoTac;
//}
