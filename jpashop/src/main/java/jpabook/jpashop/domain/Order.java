package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS") // order by가 예약어로 걸려있기 때문에 orders로 테이블명을 많이 씀
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;
    @Column(name = "MEMBER_ID")
    private Long memberId;
    private LocalDateTime orderData; // 자동으로 매핑을 해주기 때문에 손 안 대도 괜찮다.
    @Enumerated(EnumType.STRING) // ORDINAL 절대 ㄴㄴ 순서 꼬여서 큰 장애날 수 있다.
    private OrderStatus status;
}
