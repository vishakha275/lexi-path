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
import java.util.Optional;

@RestController 
@RequestMapping ("/words")

public class WordController {
    private final WordService wordService;
    private final WordRepository wordRepository;
    WordController(WordService wordService, WordRepository wordRepository){
        this.wordService=wordService;
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
    @PostMapping("/{id}/review")
    public ResponseEntity<Word> reviewWord(@PathVariable int id, @RequestParam boolean remembered){
        Optional<Word> foundWord=wordRepository.findById(id);
        if(foundWord.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Word word=foundWord.get();
        word.applyReview(remembered);
        return ResponseEntity.status(HttpStatus.CREATED).body(wordRepository.save(word));
    }
    @GetMapping
    public List<Word> getAllWords() {
        return wordService.getAllWords();
    }
    @GetMapping("/{word}")
    public ResponseEntity<Word> getWord(@PathVariable String word){
        return wordService.getWord(word)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/due-today")
    public List<Word> getDueWords(){
        return wordService.getDueWords();
    }
}
