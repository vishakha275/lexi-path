package com.vishakha.Lexi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Optional<Word> createWord(String word, String meaning){
        if(wordRepository.existsByWord(word)) return Optional.empty();

        Word newWord=new Word();
        newWord.setWord(word);
        newWord.setMeaning(meaning);
        return Optional.of(wordRepository.save(newWord));
    }

    @Transactional 
    public Optional<Word> reviewWord(int id, boolean remembered) {
        Optional<Word> reviewedWord=wordRepository.findById(id);
        if(reviewedWord.isEmpty())return Optional.empty();
        Word word=reviewedWord.get();
        word.applyReview(remembered);
        return Optional.of(wordRepository.save(word));
    }
}
