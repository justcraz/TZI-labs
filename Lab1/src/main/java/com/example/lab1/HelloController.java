package com.example.lab1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;

public class HelloController {

    @FXML
    private Button encryptButton;

    @FXML
    private Button decryptButton;

    @FXML
    private TextField shiftField;

    @FXML
    private TextArea inputArea;

    @FXML
    private TextArea outputArea;

    @FXML
    private Button loadButton;

    @FXML
    private Label error;

    @FXML
    private void clearButton(){
        inputArea.setText("");
    }


    private File selectedFile;

    @FXML
    private void handleLoadButtonAction() {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(loadButton.getScene().getWindow());
        if (selectedFile != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    inputArea.appendText(line + "\n");
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void handleEncryptButtonAction() {
        try {
            int shift = Integer.parseInt(shiftField.getText());
            String inputText = inputArea.getText();
            String outputText = caesarCipher(inputText, shift);
            outputArea.setText(outputText);
            error.setText("");
        }
            catch(NumberFormatException e) {
                error.setText("Ви залишили поле пустим, або ввели букву. Спробуйте ще раз...");

            }
        }


    @FXML
    private void handleDecryptButtonAction() {
        try {
            int shift = Integer.parseInt(shiftField.getText());
            String inputText = inputArea.getText();
            String outputText = caesarCipher(inputText, -shift);
            outputArea.setText(outputText);
            error.setText("");
        }
        catch(NumberFormatException e) {
            error.setText("Ви залишили поле пустим, або ввели букву. Спробуйте ще раз...");

        }
        }


    private String caesarCipher(String input, int shift) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    output.append((char) ('A' + (c - 'A' + shift) % 26));
                } else {
                    output.append((char) ('a' + (c - 'a' + shift) % 26));
                }
            } else {
                output.append(c);
            }
        }
        return output.toString();
    }
}