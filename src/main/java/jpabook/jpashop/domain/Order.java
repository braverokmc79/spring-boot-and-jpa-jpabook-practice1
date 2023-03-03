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

    //CascadeType.ALL 는 order
//    persist(orderItemA);
//    persist(orderItemA);
//    persist(orderItemB);
//    persist(orderItemB);
//    persist(order);

   // =>persist를 각각 해줘야 하는데 CascadeType.ALL  적용하면  persist(order); 한번에 적용된다.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems =new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Comment("주문시간")
    private LocalDateTime orderDate; //주문시간

    @Comment("주문상태")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;//주문상태 [ORDER, CANCEL]



    //==연관관계 메서드=//
    public void setMember(Member member){
        this.member =member;
        member.getOrders().add(this);
    }


    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }


    public void setDeliver(Delivery delivery){
        this.delivery=delivery;
        delivery.setOrder(this);
    }
}
