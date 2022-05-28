package br.edu.ifsp.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AutenticacaoUIController {
    @FXML
    private PasswordField txtSenha;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnEntrar;

    public void autenticar(ActionEvent actionEvent) {
    }
}
