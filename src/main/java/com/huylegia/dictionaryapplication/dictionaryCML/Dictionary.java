package com.huylegia.dictionaryapplication.dictionaryCML;

import java.util.ArrayList;

public class Dictionary {
    ArrayList <Word> words = new ArrayList<>();
    Dictionary dictionary = new Dictionary();
    public boolean checkExist(String s) {
        for (Word word:dictionary.words) {
            if (s.equalsIgnoreCase(word.getWordTarget())) {
                return true;
            }
        }
        return false;
    }
}
