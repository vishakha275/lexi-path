package com.vishakha.Lexi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@RestController 
@RequestMapping ("/words")

public class WordController {
    private final WordRepository wordRepository;

    WordController(WordRepository wordRepository){
        this.wordRepository=wordRepository;
    }

    @PostMapping
    public Word createWord(@RequestParam String word, @RequestParam String meaning) {
        Word newWord=new Word();
        newWord.setWord(word);
        newWord.setMeaning(meaning);
        return wordRepository.save(newWord);
    }
    @GetMapping
    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }
    
}
