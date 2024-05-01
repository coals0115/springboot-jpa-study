package jpabook2.jpashop2.service;

import jakarta.persistence.EntityManager;
import jpabook2.jpashop2.domain.*;
import jpabook2.jpashop2.exception.NotEnoughStockException;
import jpabook2.jpashop2.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    // 이게 좋은 테스트라고 볼 수 X
    // 좋은 테스트 = DB와 스프링에에 종속적이지 않고 정말 순수하게 메소드를 단위 테스트하는 게 좋다
    // 다만 지금은 JPA와 잘 엮여서 동작하는지 보는 게 목적이기 때문에 이렇게 통합 테스트로..
    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = createMember("회원1", new Address("서울", "강가", "123-123"));
        Book book = createBook(10000, "시골JPA", 10);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000*orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다."  ,8, book.getStockQuantity());
    }

    // 이런 예외 상황에 대한 테스트가 정말 중요하다!
    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = createMember("회원1", new Address("서울", "강가", "123-123"));
        Book book = createBook(10000, "시골 JPA", 10);

        int orderCount = 11;
        //when
        orderService.order(member.getId(), book.getId(),orderCount);

        //then
        fail("재고 수량 부족예외가 발생해야 한다.");
    }

    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = createMember("회원A", new Address("서울", "강가", "123-123"));
        Book book = createBook(10000, "시골JPA", 10);
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 재고가 증가해야 한다.", 10, book.getStockQuantity());

    }

    private Book createBook(int price, String name, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember(String s, Address address) {
        Member member = new Member();
        member.setName(s);
        member.setAddress(address);
        em.persist(member);
        return member;
    }
}