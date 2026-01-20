package com.hyunseok.spring_basic_pilot.mapper;

import com.hyunseok.spring_basic_pilot.domain.Board;
import com.hyunseok.spring_basic_pilot.dto.BoardSearchDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    List<Board> findAll();

    List<Board> findBySearch(BoardSearchDTO searchDTO);

    long countBySearch(BoardSearchDTO searchDTO);

    Optional<Board> findById(Long id);

    void insert(Board board);

    void update(Board board);

    void delete(Long id);

    void updateHit(Long id);
}
