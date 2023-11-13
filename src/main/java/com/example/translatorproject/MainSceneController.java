package com.example.translatorproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

// Đảm bảo rằng bạn đã định nghĩa phương thức static translate trong class API.
import static com.example.translatorproject.API.translate;

public class MainSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField translateTextField;
    @FXML
    private TextArea resultTextArea;

    public void initialize() {
        translateTextField.setPromptText("Search...");
        resultTextArea.setEditable(false);
        resultTextArea.setPromptText("Văn bản dịch sẽ hiển thị ở đây...");
    }

    public void handleTranslateAction(ActionEvent event) {
        String originalText = translateTextField.getText();
        String translatedText = translate(originalText);
        resultTextArea.setText(translatedText);
    }

    public void switchToHelloScene(ActionEvent event) throws IOException {
        try {
            // Tải scene mới.
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Xử lý ngoại lệ tại đây, ví dụ: hiển thị thông báo lỗi.
            e.printStackTrace();
        }
    }
}