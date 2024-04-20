package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.nio.channels.Pipe;

import static jakarta.persistence.FetchType.*;

@Entity
public class Delivery extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;
    @Embedded
    private Address address;
    private DeliveryStatus status;

}
