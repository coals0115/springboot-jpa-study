package jpabook2.jpashop2.api;

import java.time.LocalDateTime;
import java.util.List;
import jpabook2.jpashop2.domain.Address;
import jpabook2.jpashop2.domain.Order;
import jpabook2.jpashop2.domain.OrderStatus;
import jpabook2.jpashop2.repository.OrderRepository;
import jpabook2.jpashop2.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        return orderRepository.findAllByString(new OrderSearch());
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        // ORDER 2개
        // N + 1
        // 1: 첫 번째 쿼리가 나감. 여기서는 orders. 여기서 N개를 가져왔다. N(2)
        // 그러면 첫 번째 쿼리의 결과로 N번만큼 query가 추가 실행되는 게 N + 1 문제..
        // 1 + 회원 N + 배송 + N
        List<Order> orders = orderRepository.findAllByString(new OrderSearch()); // orders를 그대로 주면 절대 안됨
        return orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .toList();
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName(); // LAZY 초기화
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }
}
