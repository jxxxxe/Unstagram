package co.kr.datapia.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UnstagramRepository extends CrudRepository<Unstagram,Long> {
    List<Unstagram> findAll();

    Optional<Unstagram> findByBoardId(Long boardId);

    void deleteByBoardId(Long boardId);
}
