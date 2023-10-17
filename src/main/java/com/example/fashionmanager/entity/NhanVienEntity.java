//package com.example.fashionmanager.entity;
//
//import com.example.fashionmanager.entity.common.CommonEntity;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
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
//@Table(name = "nhan_vien_entity")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@SuperBuilder
//public class NhanVienEntity extends CommonEntity implements Serializable {
//    @Column(name = "ten_nhan_vien")
//    private String employeeName;
//    @Column(name = "cccd")
//    private String cccd;
//    @Column(name = "so_dien_thoai")
//    private String soDienThoai;
//    @Column(name = "dia_chi")
//    private String diaChi;
//    @Column(name = "gioi_tinh")
//    private boolean gioiTinh;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity userEntity;
//}
