package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "datetime default current_timestamp")
    private Date createAt;

    private Date deliveryAt;

    private String statusPay;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    private String sale;

    private Double free_ship;

    private Double totalPrice;

    private String status;

    private String address;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetailEntity> orderItems  = new ArrayList<>();

    @Column(columnDefinition = "longtext")
    private String note;

    private Date updateAt;
}