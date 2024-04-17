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
            Team team = new Team();
            team.setName("teamA");

            em.persist(team);

            Member member1 = new Member();
            member1.setName("member1");
            member1.setTeam(team);

            em.persist(member1);

            em.flush();
            em.clear();

            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();

        } finally {
            em.close(); // 얘 닫아주는 게 매우 중요. DB 커넥션을 계속 물고 있기 때문에..
            emf.close();
        }
    }
}
