package jpabook2.jpashop2;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpabook2.jpashop2.domain.Address;
import jpabook2.jpashop2.domain.Book;
import jpabook2.jpashop2.domain.Delivery;
import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.domain.Order;
import jpabook2.jpashop2.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 총 주문 2개
 * userA
 * * JPA1 BOOK
 * * JPA2 BOOK
 * userB
 * * SPRING1 BOOK
 * * SPRING2 BOOK
 */
@Component // 스프링의 Component Scan 대상이 됨.
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    // 애플리케이션 로딩 시점에 호출! | 스프링빈이 다 올라오고 나면 스프링이 호출해줌!
    // 아래 코드들을 여기에 다 넣어도 될 것 같지만,, 스프링 라이프사이클이 있어서 여기다 트랜잭션 적용하고 이런 게 안 된다(?) 그래서 별도의 빈으로 등록해야 함..
    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK", 20000, 200);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createOrder(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        public void dbInit2() {
            Member member = createMember("userB", "안산", "2", "2222");
            em.persist(member);

            Book book1 = createBook("SPRING1 BOOK", 20000, 200);
            em.persist(book1);

            Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 2);

            Delivery delivery = createOrder(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        private Member createMember(String name, String city, String zipcode, String street) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, zipcode, street));
            return member;
        }

        private Book createBook(String s, int i, int i2) {
            Book book2 = new Book();
            book2.setName(s);
            book2.setPrice(i);
            book2.setStockQuantity(i2);
            return book2;
        }

        private Delivery createOrder(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }
    }
}
