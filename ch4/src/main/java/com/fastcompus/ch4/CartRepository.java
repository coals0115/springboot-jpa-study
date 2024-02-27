package com.fastcompus.ch4;

import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> { // <엔티티 타입, 키 타입>
}
