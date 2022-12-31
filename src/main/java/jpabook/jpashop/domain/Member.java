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
    private List<Order> orders = new ArrayList<>();
}
