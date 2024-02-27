package com.fastcompus.ch4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


@SpringBootApplication
public class Ch4Application implements CommandLineRunner {
    @Autowired
    EntityManagerFactory emf;

    public static void main(String[] args) {
//        SpringApplication.run(Ch4Application.class, args);

        // 이렇게 해두면 톰캣이 안 뜬다.
        SpringApplication app = new SpringApplication(Ch4Application.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        EntityManager em = emf.createEntityManager();
        System.out.println("em = " + em);
        EntityTransaction tx = em.getTransaction();

        User user = new User();
        user.setId("aaa");
        user.setPassword("1234");
        user.setName("Lee");
        user.setEmail("aaa@aaa.com");
        user.setInDate(new Date());
        user.setUpDate(new Date());

        tx.begin(); // 트랜잭션 시작

        // 1. 저장
        em.persist(user); // user 엔티티를 em에 영속화(저장)
        em.persist(user); // 같은 user 엔티티를 여러번 저장하도 한번만 insert

        // update 문장은 모든 컬럼을 다 업데이트 하는 쿼리로 만들어진다. 그래서 엔티티 객체에 있는 값들로 다 채움
        // 이렇게 하는 이유? => 늘 같은. 하나의 sql문을 사용하려고 하는 것 그래야 성능 good, pstmt 쓰기 때문에
        // 만약 변경된 컬럼만 변경하려고 하면 그걸 다 확인해야 하고 sql문도 조합 종류가 엄청나게 많아짐. => 재사용성 떨어진다.
        // 2. 변경
        user.setPassword("4321"); // PersistenceContext가 변경감지. update
        user.setEmail("bbb@bbb.com");
        tx.commit(); // 트랜잭션 종료(DB에 반영)

        // 3. 조회
        User user2 = em.find(User.class, "aaa"); // em에 있으면 DB 조회 안함
        System.out.println("(user == user2) = " + (user == user2));
        User user3 = em.find(User.class, "bbb"); // em에 없으면 DB조회
        System.out.println("user3 = " + user3); // null, DB에 없음

        // 4. 삭제
        tx.begin();
        em.remove(user); // em에서 user 엔티티를 삭제
        tx.commit();

    }
}











