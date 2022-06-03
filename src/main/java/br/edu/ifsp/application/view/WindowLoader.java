package br.edu.ifsp.application.view;

import br.edu.ifsp.domain.entities.Dados;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class WindowLoader extends Application {

    private static Scene scene;

    private static Object controller;

    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = loadFXML("AutenticacaoUI");
        scene = new Scene(parent, 950, 700);
        stage.setTitle("Monster Rooster Gym");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml, Dados userData) throws IOException {
        scene.setRoot(loadFXML(fxml));
        notityAllListeners(fxml, userData);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        notityAllListeners(fxml, null);
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

    //---------------------------------------------------------------------

    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();
    public static interface OnChangeScreen {
        void onScreenChanged(String newScreen, Dados userData);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener) {
        listeners.add(newListener);
    }

    private static void notityAllListeners(String newScreen, Dados userData) {
        for (OnChangeScreen l : listeners) {
            l.onScreenChanged(newScreen, userData);
        }

    }
}