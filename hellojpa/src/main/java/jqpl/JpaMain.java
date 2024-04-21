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
            member.setMemberType(MemberType.ADMIN);

            member.changeTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

//            String query = "select m.username, 'HELLO', true from Member m where m.memberType = jqpl.MemberType.USER";
            String query = "select m.username, 'HELLO', true from Member m where m.memberType = :userType";

            List<Object[]> resultList = em.createQuery(query)
                    .setParameter("userType", MemberType.ADMIN)
                    .getResultList();

            for (Object[] objects : resultList) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[1] = " + objects[1]);
                System.out.println("objects[2] = " + objects[2]);
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
