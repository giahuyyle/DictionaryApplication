package com.huylegia.dictionaryapplication.dictionaryCML;
import java.util.Scanner;

public class DictionaryCommandline {
    public DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public void showAllWords() {
        int i = 1;
        System.out.println("No\t|English\t|Vietnamese");
        for (Word word:dictionaryManagement.dictionary.words) {
            System.out.println(i+"\t|"+word.getWordTarget()+"\t|"+word.getWordExplain());
            i++;
        }
    }
    public void dictionaryBasic() {
        System.out.println("Welcome to the dictionary!");
        System.out.println("Press to confirm what you want to do:");
        System.out.println("1 to insert a new word\n2 to get a list of all inserted words");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Press again to confirm:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    dictionaryManagement.insertFromCommandLine();
                    break;
                }
                case 2: {
                    showAllWords();
                    break;
                }
                default: {
                    System.out.println("Invalid input");
                }
            }
            System.out.println("Do you want to continue? (y/n)");
            String choice2 = scanner.nextLine();
            choice2 = scanner.nextLine();

            //System.out.println(choice2);
            if (choice2.equalsIgnoreCase("n")) {
                System.out.println("Thanks for using the dictionary!");
                break;
            }
        }
    }
    public void dictionaryAdvanced() {
        System.out.println("Welcome to the dictionary!");
        System.out.println("Press to confirm what you want to do:");
        System.out.println("1 to insert a new word\n2 to get a list of all inserted words\n3 to insert from file\n4 to lookup words\n5 to search using keywords\n6 to export to file\n7 to exit");
        Scanner scanner = new Scanner(System.in);
        boolean ck = true;
        while (ck) {
            System.out.println("Press again to confirm:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    dictionaryManagement.insertFromCommandLine();
                    break;
                }
                case 2: {
                    showAllWords();
                    break;
                }
                case 3: {
                    dictionaryManagement.insertFromFile();
                    break;
                }
                case 4: {
                    dictionaryManagement.dictionaryLookup();
                    break;
                }
                case 5: {
                    dictionaryManagement.dictionarySearcher();
                    break;
                }
                case 6: {
                    dictionaryManagement.exportToFile();
                    break;
                }
                case 7: {
                    ck = false;
                    System.out.println("Thanks for using!");
                    break;
                }
                default: {
                    System.out.println("Invalid input");
                }
            }
        }
    }

    public static void main(String[] args) {
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        dictionaryCommandline.dictionaryAdvanced();
    }
}
