module com.example.translatorproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires okhttp3;


    opens com.example.translatorproject to javafx.fxml;
    exports com.example.translatorproject;
}