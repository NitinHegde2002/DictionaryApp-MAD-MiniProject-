package com.example.dictionaryapp.models;

import java.util.List;

public class APIResponse {
    String word="";
    List<Meanings>meanings=null;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Meanings> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Meanings> meanings) {
        this.meanings = meanings;
    }
}
