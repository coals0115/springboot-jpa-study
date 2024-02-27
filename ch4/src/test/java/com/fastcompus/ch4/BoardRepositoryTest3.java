package com.fastcompus.ch4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest3 {
    @Autowired
    EntityManager em;

    @Autowired
    private BoardRepository boardRepo;

    @BeforeEach
    public void testData() {
        for (int i = 0; i < 100; i++) {
            Board board = new Board();
            board.setBno((long) i);
            board.setTitle("title" + i);
            board.setContent("content" + i);
            board.setWriter("writer" + (i % 5)); // writer 0~4
            board.setViewCnt((long) (Math.random() * 100)); // 0~99
            board.setInDate(new Date());
            board.setUpDate(new Date());

            boardRepo.save(board);
        }
    }


    @Test
    @DisplayName("@Query로 네이티브 쿼리(SQL)작성 테스트")
    public void queryAnnoTest5() {
//        @Query(value = "SELECT TITLE, WRITER FROM BOARD", nativeQuery = true) // SQL
//        List<Board> findAllBoardBySQL2();
        List<Object[]> list = boardRepo.findAllBoardBySQL2();
        list.stream() // list를 stream으로 변환
                .map(arr -> Arrays.toString(arr)) // arr를 String으로 변환
                .forEach(System.out::println);
        assertTrue(list.size() == 100);
    }

    @Test
    @DisplayName("@Query로 네이티브 쿼리(SQL)작성 테스트")
    public void queryAnnoTest4() {
//        @Query(value = "SELECT * FROM BOARD", nativeQuery = true) // SQL
//        List<Board> findAllBoardBySQL();
        List<Board> list = boardRepo.findAllBoardBySQL();
        assertTrue(list.size() == 100);
    }

    @Test
    @DisplayName("@Query로 JPQL 작성 테스트 - 매개변수 이름")
    public void queryAnnoTest3() {
//        @Query("SELECT b FROM Board b WHERE b.title=:title AND b.writer=:writer")
        List<Board> list = boardRepo.findByTitleAndWriter2("title1", "writer1");
        list.forEach(System.out::println);
        assertTrue(list.size() == 1);
    }

    @Test
    @DisplayName("@Query로 JPQL 작성 테스트 - 매개변수 순서")
    public void queryAnnoTest2() {
//        @Query("SELECT b FROM Board b WHERE b.title=?1 AND b.writer=?2")
        List<Board> list = boardRepo.findByTitleAndWriter2("title1", "writer1");
        list.forEach(System.out::println);
        assertTrue(list.size() == 1);
    }

    @Test
    @DisplayName("@Query로 JPQL 작성 테스트")
    public void queryAnnoTest() {
        List<Board> list = boardRepo.findAllBoard();
        list.forEach(System.out::println);
        assertTrue(list.size() == 100);

    }

    @Test
    @DisplayName("createQuery로 JPQL 작성 테스트")
    public void createQueryTest() {
        String query = "SELECT b FROM Board b";
        TypedQuery<Board> tQuery = em.createQuery(query, Board.class);
        List<Board> list = tQuery.getResultList();

        list.forEach(System.out::println);
        assertTrue(list.size() == 100);

    }



}