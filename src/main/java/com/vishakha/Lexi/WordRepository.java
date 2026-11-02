package com.vishakha.Lexi;
import java.util.Optional;
import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer> {
    public Optional<Word> findByWord(String word);
    public boolean existsByWord(String word);
    List<Word> findByDueDateLessThanEqual(LocalDate dueDate);
}
