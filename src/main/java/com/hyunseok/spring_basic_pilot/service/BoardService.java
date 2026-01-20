package com.hyunseok.spring_basic_pilot.service;

import com.hyunseok.spring_basic_pilot.domain.Board;
import com.hyunseok.spring_basic_pilot.domain.User;
import com.hyunseok.spring_basic_pilot.dto.BoardSearchDTO;
import com.hyunseok.spring_basic_pilot.dto.PageResponse;
import com.hyunseok.spring_basic_pilot.mapper.BoardMapper;
import com.hyunseok.spring_basic_pilot.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardMapper boardMapper;
    private final UserMapper userMapper;

    public PageResponse<Board> findBySearch(BoardSearchDTO searchDTO) {
        List<Board> list = boardMapper.findBySearch(searchDTO);
        long totalCount = boardMapper.countBySearch(searchDTO);

        return PageResponse.<Board>builder()
                .list(list)
                .totalCount(totalCount)
                .page(searchDTO.getPage())
                .size(searchDTO.getSize())
                .build();
    }

    public List<Board> findAll() {
        return boardMapper.findAll();
    }

    @Transactional
    public Board findById(Long id) {
        boardMapper.updateHit(id);
        return boardMapper.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
    }

    @Transactional
    public Board create(Board board, String username) {
        User user = userMapper.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        board.setAuthorId(user.getId());
        boardMapper.insert(board);
        return board;
    }

    @Transactional
    public Board update(Long id, Board board, String username) {
        Board existingBoard = boardMapper.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        User user = userMapper.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!existingBoard.getAuthorId().equals(user.getId())) {
            throw new RuntimeException("Permission denied: You are not the author.");
        }

        board.setId(id);
        boardMapper.update(board);
        return board;
    }

    @Transactional
    public void delete(Long id, String username) {
        Board existingBoard = boardMapper.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        User user = userMapper.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!existingBoard.getAuthorId().equals(user.getId())) {
            throw new RuntimeException("Permission denied: You are not the author.");
        }

        boardMapper.delete(id);
    }
}
