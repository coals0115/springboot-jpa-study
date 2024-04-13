package hellojpa;

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
            member.setName("member1");
            member.setTeam(team); // JPA가 team에서 PK를 꺼내 FK에 insert 할 때 값을 넣어준다.
            em.persist(member);

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team); // persist를 하면 id에 값이 들어간다.
            // persist를 하면 무조건 PK가 세팅이 되고 영속 상태가 된다.

            em.flush(); // flush로 영속성 컨텍스트에 있는 걸 DB에 쿼리를 날린다. / 싱크를 맞춤
            em.clear(); // 영속성 컨텍스트 초기화

            Member findMember = em.find(Member.class, member.getId()); // 기본값일 시에(EAGER?) TEAM을 같이 가져온다.(조인해서)

            List<Member> members = findMember.getTeam().getMembers();
            for (Member member1 : members) {
                System.out.println("member1 = " + member1);
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
