package com.vishakha.Lexi;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service 
public class WordService {
    private final WordRepository wordRepository;
    public WordService(WordRepository wordRepository){
        this.wordRepository=wordRepository;
    }
    public List<Word>getAllWords() {
        return wordRepository.findAll();
    }

    public Optional<Word> getWord(String word){
        return wordRepository.findByWord(word);
    }

    public List<Word> getDueWords() {
        return wordRepository.findByDueDateLessThanEqual(LocalDate.now());
    }
}
