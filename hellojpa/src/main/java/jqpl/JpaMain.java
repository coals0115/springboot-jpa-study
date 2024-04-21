package jqpl;

import jakarta.persistence.*;

import java.util.Collection;
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
            member.setMemberType(MemberType.ADMIN);

            member.changeTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            // 1. 상태 필드
//            String query = "select m.username from Member m";

            // 2. 단일 값 연관 경로
//            String query2 = "select m.team.name from Member m";
//            List<String> resultList = em.createQuery(query, String.class)
//                    .getResultList();

            // 3. 컬렉션 값 연관 경로
//            String query = "select t.members. from Team t";
            String query = "select m.username from Team t join t.members m";
            Collection resultList = em.createQuery(query, Collection.class)
                    .getResultList();

            for (Object o : resultList) {
                System.out.println("o = " + o);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();

        } finally {
            em.close(); // 얘 닫아주는 게 매우 중요. DB 커넥션을 계속 물고 있기 때문에..
            emf.close();
        }
    }
}
