package jpabook2.jpashop2.api;

import jpabook2.jpashop2.domain.Order;
import jpabook2.jpashop2.domain.OrderItem;
import jpabook2.jpashop2.repository.OrderRepository;
import jpabook2.jpashop2.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderRepository orderRepository;


    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            // 프록시(?) 강제 초기화
            order.getMember().getName();
            order.getDelivery().getAddress();

            List<OrderItem> orderItems = order.getOrderItems();
            // 오호... 그냥 for문을 람다로 대체..
            orderItems.stream().forEach(o -> o.getItem().getName());
//            for (OrderItem orderItem : orderItems) {
//                orderItem.getItem().getName();
//            }
        }

        return all;
    }
}
