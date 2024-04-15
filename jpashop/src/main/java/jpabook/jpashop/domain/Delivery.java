package jpabook.jpashop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.nio.channels.Pipe;

@Entity
public class Delivery extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    @OneToOne(mappedBy = "delivery")
    private Order order;
    private String city;
    private String zipcode;
    private DeliveryStatus status;

}
