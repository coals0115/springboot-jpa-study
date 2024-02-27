package com.fastcompus.ch4;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

// 이렇게만 하면 끝!!
public interface BoardRepository extends CrudRepository<Board, Long> { // <엔티티 타입, 키 타입>
    // SELECT COUNT(*) FROM BOARD WHERE WRITER = :writer
    int countByWriter(String writer);

    // SELECT * FROM BOARD WHERE WRITER = :writer
    List<Board> findByWriter(String writer);

    // SELECT * FROM BOARD WHERE TITLE = :title AND WRITER = :writer
    List<Board> findByTitleAndWriter(String title, String writer);

    // DELETE FROM BOARD WHERE WRITER = :writer
    int deleteByWriter(String writer);
}
