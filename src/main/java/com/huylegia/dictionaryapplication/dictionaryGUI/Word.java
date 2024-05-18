package com.huylegia.dictionaryapplication.dictionaryGUI;

public class Word implements Comparable<Word> {
    private String wordTarget;
    private String wordPhonetic;
    private String wordExplain;

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public String getWordPhonetic() {
        return wordPhonetic;
    }

    public void setWordPhonetic(String wordPhonetic) {
        this.wordPhonetic = wordPhonetic;
    }

    public Word(String wordTarget, String wordPhonetic, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.wordPhonetic = wordPhonetic;
    }

    public int compareTo(Word word) {
        return this.getWordTarget().compareToIgnoreCase(word.getWordTarget());
    }
}
