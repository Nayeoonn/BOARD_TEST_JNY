package com.aloha.board;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aloha.board.dto.Board;
import com.aloha.board.service.BoardService;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
class BoardApplicationTests {

	@Autowired
    private BoardService boardService;

	@Test
	void contextLoads() {
		// log.info("SPRING BOARD 테스트");
		// log.info("테스트를 실행합니다.");
	}

	@Test
	@Order(1)
	void insert() throws Exception {
		Board board1 = new Board();
		board1.setTitle("title1");
		board1.setWriter("writer1");
		board1.setContent("content1");
		Board board2 = new Board();
		board2.setTitle("title2");
		board2.setWriter("writer2");
		board2.setContent("content2");
		Board board3 = new Board();
		board3.setTitle("title3");
		board3.setWriter("writer3");
		board3.setContent("content3");

		int result = 0;
		result += boardService.insert(board1);
		result += boardService.insert(board2);
		result += boardService.insert(board3);

		if( result >= 3 ) {
			assertTrue(true);
			// log.info("게시글 등록 3건 성공!");
		} else {
			fail("게시글 등록 실패!");
		}

	}


	@Test
	@Order(2)
	void select() throws Exception {
		int no = 1;
		Board board = boardService.select(no);

		if( board == null ) {
			// log.error("게시글 조회 실패 - board is null");
			// log.error("글번호 1번이 반드시 있어야합니다.");
			fail("게시글 조회 실패!");
		}

		if( board !=null ) {
			assertTrue(true);
			// log.info("게시글 조회 성공!");
		} else {
			// log.error("글번호 1번이 반드시 있어야합니다.");
			fail("게시글 조회 실패!");
		}

	}

	@Test
	@Order(3)
	void update() throws Exception {
		int no = 1;
		Board board = boardService.select(no);
		board.setTitle("updated title");
		board.setWriter("updated writer");
		board.setContent("updated content");

		int result = boardService.update(board);

		if( result > 0 ) {
			assertTrue(true);
			// log.info("게시글 수정 성공!");
		} else {
			fail("게시글 수정 실패!");
			// log.error("글번호 1번이 반드시 있어야합니다.");
		}

	}

	@Test
	@Order(4)
	void delete() throws Exception {
		int no = 1;

		int result = boardService.delete(no);

		if( result > 0 ) {
			assertTrue(true);
			// log.info("게시글 삭제 성공!");
		} else {
			fail("게시글 삭제 실패!");
			// log.error("글번호 1번이 반드시 있어야합니다.");
		}

	}


	@Test
	@Order(5)
    void testList() throws Exception {
        List<Board> boardList = boardService.list();

		if(boardList == null ) {
			// log.error("게시글 목록 조회 실패 - boardList is null");
			return;
		}
		// log.info("게시글 개수 : " + boardList.size());
        if (boardList.size() >= 2) {
            assertTrue(true);
			// log.info("게시글 목록 조회 성공!");
        } else {
			// log.error("게시글 목록 조회 실패 - 게시글 최소 2개 이상");
            fail("검증 실패: 리스트의 크기가 2보다 작습니다.");
        }
	}


}
