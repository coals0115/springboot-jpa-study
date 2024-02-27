package com.fastcompus.ch4;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// 이렇게만 하면 끝!!
public interface BoardRepository extends CrudRepository<Board, Long> { // <엔티티 타입, 키 타입>
    @Query(value = "SELECT TITLE, WRITER FROM BOARD", nativeQuery = true) // SQL
    List<Object[]> findAllBoardBySQL2();

   @Query(value = "SELECT * FROM BOARD", nativeQuery = true) // SQL
   List<Board> findAllBoardBySQL();

    @Query("SELECT b FROM Board b") // JPQL은 명칭을 대소문자 구분에 주의
    List<Board> findAllBoard(); // 메서드 이름은 아무거나 해도 괜찮습니다.

    @Query("SELECT b FROM Board b WHERE b.title=?1 AND b.writer=?2")
    List<Board> findByTitleAndWriter2(String title, String write);

    // SELECT COUNT(*) FROM BOARD WHERE WRITER = :writer
    int countAllByWriter(String writer);

    // SELECT * FROM BOARD WHERE WRITER = :writer
    List<Board> findByWriter(String writer);

    // SELECT * FROM BOARD WHERE TITLE = :title AND WRITER = :writer
    List<Board> findByTitleAndWriter(String title, String writer);

    // DELETE FROM BOARD WHERE WRITER = :writer
    @Transactional // delete의 경우, 여러 건을 delete 할 수 있기 때문에 Tx처리 필수
    int deleteByWriter(String writer);
}
