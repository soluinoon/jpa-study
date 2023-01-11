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

    // to many 시리즈는 기본이 레이지라 괜찮은데 투 원는 무조건 페치타입 레이지로 다 바꿔줘야 함.
    @ManyToOne(fetch = FetchType.LAZY) // 양방향이라 연관관게 주인 정해줘야 함.
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 오더에 포린키를 주자. 접근이 더 많이 일어나므로
    @JoinColumn(name = "delivery")
    private Delivery delivery;

    private LocalDateTime orderTime; // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [ORDER, CANCLE]

    //==연관관계 메서드==//
    /*
    ex) 다음과 같다.
    psvm() {
        Member member = new Member();
        Order order = new Order();

        member.getOrders().add(order);
        order.setMember(member);
    }
     */
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    /*
    ex) 위 코드를 작성한다면 아래와 같아짐 편의성 증가
    psvm() {
        Member member = new Member();
        Order order = new Order();

        order.setMember(member);
    }
     */
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
