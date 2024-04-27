package jpabook2.jpashop2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    // 커맨드와 쿼리를 분리하라는 원칙?
    // 저장 후에는 사이드이펙트를 일으키는 커맨드성이기 때문에 리턴값을 안 만든다?
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

}
