package jpabook2.jpashop2;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class) // Junit에게 Spring 관련된 걸로 테스트 할 거라고 알려줌
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("memberA");

        // when
        Long saveId = memberRepository.save(member); // 1. 멤버 저장
        Member findMember = memberRepository.find(saveId);// 2. 저장한 멤버로 검색

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        // 영속성 컨텍스트에서 식별자가 같으면 같은 엔티티로 인식(같은 트랜잭션), 1차 캐시에서 꺼냄
        Assertions.assertThat(findMember).isEqualTo(member);
    }

}