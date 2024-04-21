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
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            Query query2 = em.createQuery("select m from Member m");
            TypedQuery<Integer> query3 = em.createQuery("select m.age from Member m", Integer.class);

            em.flush();
            em.clear();

            List<Member> result = query1.getResultList();
            Member member1 = result.get(0);
            member1.setAge(20);

            // JPQL은 SQL과 비슷하게 써야 한다. 아래와 같은 방식은 권장하지 않음 이렇게 짜면 TEAM과 조인 쿼리라 나갈 거라는 예측이 되지 않는다.
            TypedQuery<Team> query4 = em.createQuery("select m.team from Member m", Team.class);
            List<Team> query5 = em.createQuery("select t from Member m join m.team t", Team.class).getResultList();
            // 얘는 Order 안에 있는 값이기 때문에 문제X
            em.createQuery("select o.address from Order o", Address.class).getResultList();

            List<MemberDTO> resultList = em.createQuery("select new jqpl.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();
            MemberDTO memberDTO = resultList.get(0);
            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();

        } finally {
            em.close(); // 얘 닫아주는 게 매우 중요. DB 커넥션을 계속 물고 있기 때문에..
            emf.close();
        }
    }
}
