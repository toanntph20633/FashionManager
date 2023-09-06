package com.example.fashionmanager.entity;

import com.example.fashionmanager.entity.common.CommonEntity;
import com.example.fashionmanager.enums.BuyingMethod;
import com.example.fashionmanager.enums.OrderStatus;
import com.example.fashionmanager.enums.PaymentMethod;
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
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "order_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity extends CommonEntity implements Serializable {
    @Column(name = "order_code")
    private String orderCode;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "original_total")
    private BigDecimal originalTotal;
    @Column(name = "discount_total")
    private BigDecimal dicountTotal;
    @Column(name = "shipping_price")
    private BigDecimal shippingPrice;
    @Column(name = "final_total")
    private BigDecimal finalTotal;
    @Column(name = "note", columnDefinition = "LONGTEXT")
    private String note;
    @Column(name = "buying_method")
    @Enumerated(EnumType.STRING)
    private BuyingMethod buyingMethod;
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Column(name = "order_satus")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeEntity;

}
