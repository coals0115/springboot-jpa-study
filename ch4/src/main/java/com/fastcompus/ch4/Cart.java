package com.fastcompus.ch4;

import jakarta.persistence.*;

@Entity
public class Cart {
    @Id
    @Column(name="cart_id") // 실제 DB 컬럼 이름을 cart_id로 지정
    private Long id;
    @OneToOne // FK가 생김
    @JoinColumn(name="member_id", nullable = false) // inner join
    private Member member;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
//                ", member=" + member +
                '}';
    }
}
