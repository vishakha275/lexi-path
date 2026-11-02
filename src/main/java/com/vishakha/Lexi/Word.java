package com.vishakha.Lexi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import jakarta.persistence.Column;


@Entity 
@Table(name="words")
public class Word {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String meaning;
    @Column(unique=true)
    private String word;
    private LocalDate dueDate=LocalDate.now();
    private int gap=0;

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public int getGap() {
        return gap;
    }
    public void setGap(int gap) {
        this.gap = gap;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    } 
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getMeaning() {
        return meaning;
    }
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
