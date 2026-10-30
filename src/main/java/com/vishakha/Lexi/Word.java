package com.vishakha.Lexi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name="words")
public class Word {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private String word;
    private String meaning;
}
