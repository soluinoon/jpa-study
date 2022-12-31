package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 내장될 수 있다.
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // jpa에선 프로텍티드 까진 가능하게 한다.
    // jpa인 것을 알 수 있음.
    // 값 타입은 변경 불가능하게 설계
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
