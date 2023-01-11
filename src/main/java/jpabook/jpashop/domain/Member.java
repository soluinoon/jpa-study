package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장
    private Address address;

    @OneToMany(mappedBy = "member") // 주인이 아니기 때문
    // 컬렉션은 필드에서 바로 초기화하는게 좋음!!!
    private List<Order> orders = new ArrayList<>();

    /*
    public Member() {
        orders = new ArrayList<>();
    }
    이런 식으로 하면 널을 고려해야 함.
    그리고 가장 중요한 건 하이버네이트는 엔티티를 영속화 할 때, 컬렉션을 하이버네이트 내장 컬렉션으로 전환(감싼다)
    따라서 임의접근 시 오류가 발생할 수 있음.
    만들고 건들지말자.
     */
}
