package jpabook2.jpashop2.repository;

import jakarta.persistence.EntityManager;
import jpabook2.jpashop2.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }
    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

//    public List<Order> findAll(OrderSearch orderSearch){}
}