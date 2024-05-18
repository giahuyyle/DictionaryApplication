package com.huylegia.dictionaryapplication;

import com.huylegia.dictionaryapplication.dictionaryGUI.DictionaryManagement;
import com.huylegia.dictionaryapplication.dictionaryGUI.Word;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {
    public static DictionaryManagement dictionaryManagement = new DictionaryManagement();
    @FXML
    private TextField searchTextField;
    @FXML
    private ListView<String> searchListView;
    @FXML
    private ListView<String> historyListView;
    @FXML
    private ListView<String> favListView;
    @FXML
    private Label searchWord, searchPhonetic;
    @FXML
    private TextArea searchDefinition;
    @FXML
    private ToggleButton buttonFavorite;
    void updateListView(){
        ArrayList<String> list = dictionaryManagement.dictionarySearcher(searchTextField.getText());
        searchListView.getItems().clear();
        searchListView.setItems(FXCollections.observableArrayList(list));
        searchListView.refresh();
    }
    void updateFavoriteListView() {
        ArrayList<Word> list = dictionaryManagement.dictionary.favWords;
        favListView.getItems().clear();
        for (Word word:list) {

            favListView.getItems().add(word.getWordTarget()+"\t"+word.getWordPhonetic()+"\n");
        }
        favListView.refresh();
    }
    void updateHistoryListView() {
        ArrayList<Word> list = dictionaryManagement.dictionary.historyWords;
        historyListView.getItems().clear();
        for (Word word:list) {
            historyListView.getItems().add(word.getWordTarget()+"\t"+word.getWordPhonetic()+"\n");
        }
        historyListView.refresh();
    }
    void addHistoryWord(Word word) {
        ArrayList <Word> list = dictionaryManagement.dictionary.historyWords;
        for (Word word1:list) {
            if (word1.compareTo(word) == 0) {
                list.remove(word1);
                break;
            }
        }
        list.add(0, word);
        updateHistoryListView();
    }
    void addFavoriteWord(Word word) {
        ArrayList <Word> list = dictionaryManagement.dictionary.favWords;
        list.add(0,word);
        updateFavoriteListView();
    }
    void removeFavoriteWord(Word word) {
        ArrayList <Word> list = dictionaryManagement.dictionary.favWords;
        for (Word word1: list) {
            if (word.compareTo(word1)==0) {
                list.remove(word1);
            }
        }
        updateFavoriteListView();
    }
    void lookupSearch(){
        Word word = dictionaryManagement.dictionaryLookup(searchTextField.getText());
        if(word!=null){
            searchWord.setText(word.getWordTarget());
            searchPhonetic.setText(word.getWordPhonetic());
            searchDefinition.setText(word.getWordExplain());
            addHistoryWord(word);
            updateFavoriteButton();
        }

    }
    void lookUpSearch2() {
        Word word = dictionaryManagement.dictionaryLookup(searchListView.getSelectionModel().getSelectedItem());
            if (word!=null) {
                searchWord.setText(word.getWordTarget());
                searchPhonetic.setText((word.getWordPhonetic()));
                searchDefinition.setText(word.getWordExplain());
                addHistoryWord(word);
                updateFavoriteButton();
            }
    }
    void updateFavoriteButton() {
        ArrayList <Word> list = dictionaryManagement.dictionary.favWords;
        for (Word word1:list) {
            if (searchWord.getText().equals(word1.getWordTarget())) {
                buttonFavorite.isSelected();
            }
            else buttonFavorite.setSelected(false);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateFavoriteListView();
        updateHistoryListView();
        buttonFavorite.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (me.getButton().equals(MouseButton.PRIMARY)) {
                    if (searchWord.getText().equals("English - Vietnamese")) {
                        buttonFavorite.setSelected(false);
                    }
                    else {
                        if (buttonFavorite.isSelected()) {
                            addFavoriteWord(dictionaryManagement.dictionaryLookup(searchWord.getText()));
                        }
                        else {
                            removeFavoriteWord(dictionaryManagement.dictionaryLookup(searchWord.getText()));
                        }
                    }
                }
            }
        });
        searchTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                   lookupSearch();
                }
                if (ke.getCode() == KeyCode.DOWN){
                    searchListView.requestFocus();
                    searchListView.getSelectionModel().selectFirst();
                }
                updateListView();
            }
        });
        searchListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (me.getButton().equals(MouseButton.PRIMARY)) {
                    lookUpSearch2();
                }
            }
        });
        searchListView.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    lookUpSearch2();
                }
                else if (ke.getCode() == KeyCode.UP && searchListView.getSelectionModel().isSelected(0)) {
                    searchTextField.requestFocus();
                }
            }
        });
    }
}