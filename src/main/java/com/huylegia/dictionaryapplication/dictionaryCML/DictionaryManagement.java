package com.huylegia.dictionaryapplication.dictionaryCML;

import java.io.*;
import java.util.Scanner;


public class DictionaryManagement {
    public Dictionary dictionary = new Dictionary();

    public void insertFromCommandLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert your word:");
        String wordTarget = scanner.nextLine();
        if (dictionary.checkExist(wordTarget)==false) {
            System.out.println("Please insert the word's translation:");
            String wordExplain = scanner.nextLine();
            dictionary.words.add(new Word(wordTarget, wordExplain));
            System.out.println("Successfully added word!");
        }
        else {
            System.out.println("Word already exists.");
        }
    }
    public void insertFromFile() {
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/com/huylegia/dictionaryapplication/dictionary.txt"));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] s = line.split("\t");
                if (s.length==2 && !s[0].isBlank() && !s[1].isBlank()) {
                    dictionary.words.add(new Word(s[0], s[1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
    public void dictionaryLookup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert your word:");
        String s = scanner.nextLine();
        for (Word word:dictionary.words) {
            if (s.equalsIgnoreCase(word.getWordTarget())) {
                System.out.println(word.getWordExplain());
            }
        }
    }
    public void dictionarySearcher() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert your search keys:\n");
        String s = scanner.nextLine();
        System.out.println("Search results:");
        String list = "";
        for (Word word:dictionary.words) {
            if (s.equalsIgnoreCase(word.getWordTarget().substring(0, s.length()))) {
                list+= word.getWordTarget() + ", ";
            }
        }
        if (list.length()==0) System.out.println("There are no suitable words.");
        else System.out.println(list.substring(0, list.length()-2));
    }
    public void exportToFile() {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/com/huylegia/dictionaryapplication/dictionaryexp.txt");
            for (Word word:dictionary.words) {
                fileWriter.write(word.getWordTarget()+"\t"+word.getWordExplain()+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
