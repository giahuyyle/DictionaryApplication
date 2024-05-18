package com.huylegia.dictionaryapplication.dictionaryGUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class DictionaryManagement {
    public Dictionary dictionary = new Dictionary();
    public DictionaryManagement(){
        insertFromFile();
        loadData();
    }
    public boolean checkExist(String s) {
        for (Word word:dictionary.words) {
            if (s.equalsIgnoreCase(word.getWordTarget())) {
                return true;
            }
        }
        return false;
    }
    public void insert(String wordTarget, String wordPhonetic, String wordExplain) {
        dictionary.words.add(new Word(wordTarget, wordPhonetic, wordExplain));
    }
    public void remove(String wordTarget) {
        for (Word word: dictionary.words) {
            if (wordTarget.equalsIgnoreCase(word.getWordTarget())) {
                dictionary.words.remove(word);
            }
        }
    }
    public void edit(String wordTarget, String wordPhonetic, String wordExplain) {
        for (Word word: dictionary.words) {
            if (wordTarget.equalsIgnoreCase(word.getWordTarget())) {
                word.setWordPhonetic(wordPhonetic);
                word.setWordExplain(wordExplain);
                break;
            }
        }
    }
    public void insertFromFile() {
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/com/huylegia/dictionaryapplication/dictionary.txt"));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] s = line.split("\t");
                if (s.length==2 && !s[0].isBlank() && !s[1].isBlank()) {
                    dictionary.words.add(new Word(s[0], "", s[1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
    public Word dictionaryLookup(String wordTarget) {
        for (Word word:dictionary.words) {
            if (wordTarget.equalsIgnoreCase(word.getWordTarget())) {
                return word;
            }
        }
        return null;
    }
    public ArrayList<String> dictionarySearcher(String s) {
        ArrayList <String> list = new ArrayList<>();
        for (Word word:dictionary.words) {
            if (s.equalsIgnoreCase(word.getWordTarget().substring(0, s.length()))) {
                list.add(word.getWordTarget());
            }
        }
        return list;
    }
    public void exportToFile() {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/com/huylegia/dictionaryapplication/dictionaryexp.txt");
            for (Word word:dictionary.words) {
                fileWriter.write(word.getWordTarget()+"\t"+word.getWordPhonetic()+"\t"+word.getWordExplain()+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void loadHistory() {
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/com/huylegia/dictionaryapplication/historyword.txt"));
            while (scanner.hasNext()) {
                String historyTarget = scanner.nextLine();
                if (historyTarget.equals("")) break;
                dictionary.historyWords.add(dictionaryLookup(historyTarget));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void saveHistory() {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/com/huylegia/dictionaryapplication/historyword.txt");
            for (Word word:dictionary.historyWords) {
                fileWriter.write(word.getWordTarget()+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void saveFavorite() {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/com/huylegia/dictionaryapplication/favword.txt");
            for (Word word : dictionary.favWords) {
                fileWriter.write((word.getWordTarget()) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void loadFavorite() {
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/com/huylegia/dictionaryapplication/favword.txt"));
            while (scanner.hasNext()) {
                String favTarget = scanner.nextLine();
                if (favTarget.equals("")) break;
                dictionary.favWords.add(dictionaryLookup(favTarget));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void saveData() {
        saveHistory();
        saveFavorite();
    }
    public void loadData() {
        insertFromFile();
        loadHistory();
        loadFavorite();
    }
}
