package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    // @Enumerated(EnumType.ORDINAL) // 숫자로 들어감 -> 1 2 였는데 중간에 다른 상태값 추가시키면 값이 밀리면서 망함
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // READY, COMP
}
