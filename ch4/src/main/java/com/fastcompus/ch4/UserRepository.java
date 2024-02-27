package com.fastcompus.ch4;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// 이렇게만 하면 끝!!
public interface UserRepository extends CrudRepository<User, String> { // <엔티티 타입, 키 타입>
}
