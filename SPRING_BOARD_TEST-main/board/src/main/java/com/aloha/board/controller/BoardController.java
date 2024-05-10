package com.aloha.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.board.dto.Board;
import com.aloha.board.service.BoardService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
@RequestMapping("/board")
public class BoardController {
    
    @Autowired
    private BoardService boardService;


    /**
     * 게시글 목록 화면
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public String list(Model model) throws Exception{
        List<Board> boardList = boardService.list();
        model.addAttribute("boardList", boardList);
        return "/board/list";

    }
    
    /**
     * 게시글 조회 화면
     * @param model
     * @param no
     * @return
     * @throws Exception
     */
    @GetMapping("/read")
    public String select(Model model, @RequestParam ("no") int no) throws Exception {
        Board board = boardService.select(no);
        model.addAttribute("board", board);
        return "/board/read";
    }

    /**
     * 게시글 등록 화면
     * @return
     */
    @GetMapping("/insert")
    public String insert() {
        return "/board/insert";
    }
    
    
    /**
     * 게시물 등록 처리
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/insert")
    public String insertPro(Board board) throws Exception {

        int result = boardService.insert(board);

        if ( result > 0 ){
            return "redirect:/board/list";
        }
        return "redirect:/board/insert";
    }
    
    /**
     * 게시물 수정 화면
     * @param model
     * @param no
     * @return
     * @throws Exception
     */
    @GetMapping("/update")
    public String update(Model model, @RequestParam ("no") int no) throws Exception {
        Board board = boardService.select(no);
        model.addAttribute("board", board);
        return "/board/update";
    }

    @PostMapping("/update")
    public String updatePro(Board board, @RequestParam ("no") int no) throws Exception {
        
        int result = boardService.update(board);
        if ( result > 0){
            return "redirect:/board/list";
        }
        
        return "redirect:/board/update?no=" + board.getNo();
    }

    @PostMapping("/delete")
    public String delete(@RequestParam ("no") int no) throws Exception {
        
        int result = boardService.delete(no);
        
        if(result > 0 ){
            return "redirect:/board/list";
        }
        return "redirect:/board/update?no=" + no + "&error";
    }
    
    
    
}
