package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id") // 테이블_아이디로 맞추는게 좋음
    private Long id;

    @ManyToOne // 양방향이라 연관관게 주인 정해줘야 함.
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne // 오더에 포린키를 주자. 접근이 더 많이 일어나므로
    @JoinColumn(name = "delivery")
    private Delivery delivery;

    private LocalDateTime orderTime; // 주문시간

    private OrderStatus status; // 주문 상태 [ORDER, CANCLE]
}
