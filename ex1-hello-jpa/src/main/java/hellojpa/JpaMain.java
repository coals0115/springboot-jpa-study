package hellojpa;

import hellojpa.item.Movie;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // 팩토리를 만드는 순간 DB 연결된 것..
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // persistence.xml에 적혀져있는 거
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setName("hello");

            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println("findMember.getName() = " + findMember.getName());
            System.out.println("emf.getPersistenceUnitUtil().isLoaded(findMember) = " + emf.getPersistenceUnitUtil().isLoaded(findMember));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();

        } finally {
            em.close(); // 얘 닫아주는 게 매우 중요. DB 커넥션을 계속 물고 있기 때문에..
            emf.close();
        }
    }
}
