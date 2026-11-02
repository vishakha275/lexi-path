package com.vishakha.Lexi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.time.LocalDate;


@RestController 
@RequestMapping ("/words")

public class WordController {
    private final WordRepository wordRepository;

    WordController(WordRepository wordRepository){
        this.wordRepository=wordRepository;
    }

    @PostMapping
    public ResponseEntity<Word> createWord(@RequestParam String word, @RequestParam String meaning) {
        if(wordRepository.existsByWord(word)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Word newWord=new Word();
        newWord.setWord(word);
        newWord.setMeaning(meaning);
        return ResponseEntity.status(HttpStatus.CREATED).body(wordRepository.save(newWord));
    }
    @GetMapping
    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }
    @GetMapping("/{word}")
    public ResponseEntity<Word> getWord(@PathVariable String word){
        return wordRepository.findByWord(word)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/due-today")
    public List<Word> dueWords(){
        return wordRepository.findByDueDateLessThanEqual(LocalDate.now());
    }
}
