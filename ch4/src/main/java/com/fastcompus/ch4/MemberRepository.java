package com.fastcompus.ch4;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> { // <엔티티 타입, 키 타입>



}
