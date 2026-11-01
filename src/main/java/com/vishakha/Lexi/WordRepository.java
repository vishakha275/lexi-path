package com.vishakha.Lexi;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface WordRepository extends JpaRepository<Word, Integer> {
    public Optional<Word> findByWord(String word);
    public boolean existsByWord(String word);
}
