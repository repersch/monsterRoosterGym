package br.edu.ifsp.application.view;

import br.edu.ifsp.domain.entities.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class WindowLoader extends Application {

    private static Scene scene;

    private static Object controller;

    private  static Usuario usuarioLogado;

    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = loadFXML("AutenticacaoUI");
        scene = new Scene(parent, 950, 700);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WindowLoader.class.getResource(fxml + ".fxml"));
        controller = fxmlLoader.getController();
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Object getController() {
        return controller;
    }

}