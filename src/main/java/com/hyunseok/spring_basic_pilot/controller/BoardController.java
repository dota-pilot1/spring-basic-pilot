package com.hyunseok.spring_basic_pilot.controller;

import com.hyunseok.spring_basic_pilot.domain.Board;
import com.hyunseok.spring_basic_pilot.dto.BoardSearchDTO;
import com.hyunseok.spring_basic_pilot.dto.PageResponse;
import com.hyunseok.spring_basic_pilot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public List<Board> getBoards() {
        return boardService.findAll();
    }

    @GetMapping("/search")
    public PageResponse<Board> getBoardsPaged(BoardSearchDTO searchDTO) {
        return boardService.findBySearch(searchDTO);
    }

    @GetMapping("/{id}")
    public Board getBoard(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @PostMapping
    public Board createBoard(@RequestBody Board board, Authentication authentication) {
        return boardService.create(board, authentication.getName());
    }

    @PutMapping("/{id}")
    public Board updateBoard(@PathVariable Long id, @RequestBody Board board, Authentication authentication) {
        return boardService.update(id, board, authentication.getName());
    }

    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable Long id, Authentication authentication) {
        boardService.delete(id, authentication.getName());
        return "Deleted successfully";
    }
}
