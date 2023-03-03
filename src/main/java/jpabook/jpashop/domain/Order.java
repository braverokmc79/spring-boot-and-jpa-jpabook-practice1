package jpabook.jpashop.domain;

import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems =new ArrayList<>();


    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Comment("주문시간")
    private LocalDateTime orderDate; //주문시간

    @Comment("주문상태")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;//주문상태 [ORDER, CANCEL]

}