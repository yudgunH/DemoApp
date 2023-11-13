package com.example.translatorproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.translatorproject.API2.translate2;

public class Main2SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField translateTextField;
    @FXML
    private TextField resultTextArea; // Đã loại bỏ từ khóa static.

    public void initialize() {
        translateTextField.setPromptText("Nhập văn bản tiếng Việt...");
        resultTextArea.setEditable(false);        resultTextArea.setPromptText("Văn bản dịch sẽ hiển thị ở đây...");
    }

    public void handleTranslateAction(ActionEvent event) {
        String originalText = translateTextField.getText();
        String translatedText = translate2(originalText);
        resultTextArea.setText(translatedText);
    }
    public void switchToHelloScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
