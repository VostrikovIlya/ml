package ru.rctikk.ml.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.rctikk.ml.entity.Words;

import java.util.List;

@Repository
public interface WordsRepo extends JpaRepository<Long, Words> {
    @Query("select name from words")
    List<String> findAllName();
}
