module com.huylegia.dictionaryapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.huylegia.dictionaryapplication to javafx.fxml;
    exports com.huylegia.dictionaryapplication;
}