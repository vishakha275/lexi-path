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

    WordController(WordService wordService){
        this.wordService=wordService;
    }

    @PostMapping
    public ResponseEntity<Word> createWord(@RequestParam String word, @RequestParam String meaning) {
        Optional<Word> createdWord=wordService.createWord(word, meaning);
        if(createdWord.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWord.get());
    }
    @PostMapping("/{id}/review")
    public ResponseEntity<Word> reviewWord(@PathVariable int id, @RequestParam boolean remembered){
        return wordService.reviewWord(id, remembered)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
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
