package ru.rctikk.ml.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rctikk.ml.entity.Button;

import java.util.Optional;

@Repository
public interface ButtonRepo extends JpaRepository<Button,Long> {
    Optional<Button> findButtonByHash(String hash);
}
