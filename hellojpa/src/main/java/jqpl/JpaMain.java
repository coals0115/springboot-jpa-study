package jqpl;

import jakarta.persistence.*;

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

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);

            member.changeTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            // Member와 Team inner join.. | join column 애너테이션이 있으니까 알아서 해주는 건가..?
            List<Member> resultList = em.createQuery("select m from Member m inner join m.team t", Member.class).getResultList();

//            select * from member m ,team t where m.team_id = t.id;

            tx.commit();
        } catch (Exception e) {
            tx.rollback();

        } finally {
            em.close(); // 얘 닫아주는 게 매우 중요. DB 커넥션을 계속 물고 있기 때문에..
            emf.close();
        }
    }
}
